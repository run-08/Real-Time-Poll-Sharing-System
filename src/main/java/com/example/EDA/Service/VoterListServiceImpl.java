package com.example.EDA.Service;

import com.example.EDA.DTO.EmailBasedVoterListRequestDTO;
import com.example.EDA.DTO.PollVoteRequestDTO;
import com.example.EDA.Exceptions.InvalidOptionException;
import com.example.EDA.Exceptions.VoterListAlreadyExist;
import com.example.EDA.Exceptions.VoterListPollIDExistException;
import com.example.EDA.Repository.EmailBasedVoterListRepo;
import com.example.EDA.Repository.VoterListRepo;
import com.example.EDA.VoterListModel.EmailBasedVoterList;
import com.example.EDA.VoterListModel.VoterList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoterListServiceImpl implements VoterListService {

    private final VoterListRepo voterListRepo;
    private final EmailBasedVoterListServiceImpl emailBasedVoterListService;
    @Override
    public Mono<VoterList> savePoll(VoterList voterList) {
        log.info("VoterList: {}",voterList);

        return isPollExist(voterList.getPollId())
                .flatMap(isExist -> {
                    if(isExist){
                        return Mono.error(new VoterListAlreadyExist(voterList.getPollId()));
                    }

                    return voterListRepo.save(voterList);
                });
    }
    @Override
    public Flux<VoterList> getAllPolls() {
        log.info("Request for Accessing all the polls gotten!");
        return
                voterListRepo.findAll();
    }
    @Override
    public Mono<VoterList> votePoll(PollVoteRequestDTO pollVoteRequestDTO) {
        log.info("Service for voting: ");
        log.info("pollVoteRequestDTO: {}",pollVoteRequestDTO);
        String pollId = pollVoteRequestDTO.getPollId();
        Integer option = pollVoteRequestDTO.getOption();
        String email = pollVoteRequestDTO.getEmail();
        EmailBasedVoterListRequestDTO emailBasedVoterListRequestDTO =
                EmailBasedVoterListRequestDTO
                        .builder()
                        .emailId(email)
                        .pollId(pollId)
                        .build();
        emailBasedVoterListService
                .saveUserVote(emailBasedVoterListRequestDTO);
        email = email.replace(".","_");
        String finalEmail = email;
        return
               isPollExist(pollId)
                       .flatMap(isExist -> {
                       if(!isExist){
                           return Mono.error(new Exception("Poll ID doesn't exist!"));
                       }
                       return voterListRepo.findByPollId(pollId);
                       })
                       .flatMap( voterList -> {
                           if(!voterList.getOptions().contains(option)){
                                return Mono.error(new InvalidOptionException(option));
                           }
                           voterList.getVoted().put(finalEmail,option);
                           log.info("Updated: "+voterList.getVoted().containsKey(finalEmail));
                           return voterListRepo.save(voterList);
                       });
    }

    @Override
    public Mono<Boolean> isPollExist(String pollId) {
        log.info("Service for identifying if id exist or not: "+(pollId));
                return
                        voterListRepo
                                .findByPollId(pollId)
                                .map(voterList -> true)
                                .defaultIfEmpty(false);

    }

        @Override
        public Mono<VoterList> deleteVoterList(String pollId,String emailId) {
            log.info("Service for deleting the poll by Id : "+pollId);
            emailBasedVoterListService.deleteEmailBasedUserVote(emailId,pollId);
                return
                        voterListRepo
                                .findById(pollId)
                                .switchIfEmpty(Mono.error(new VoterListPollIDExistException(pollId)))
                                .flatMap(voterList -> voterListRepo.deleteByPollId(voterList.getPollId()));

        }

    @Override
    public Mono<Map<String, Integer>> getUserVotes(String email, List<String> pollIds) {
        email = email.replace(".", "_");
        String finalEmail = email;
        return Flux.fromIterable(pollIds)
                .flatMap(pollId ->
                        voterListRepo
                                .findByPollId(pollId)
                                .map(voterList -> {
                                    Map<String,Integer> voted = voterList.getVoted();
                                    Integer option = voted.getOrDefault(finalEmail, -1);
                                    return Map.entry(pollId, option);
                                })
                                .defaultIfEmpty(Map.entry(pollId, -1))
                )
                .collectMap(Map.Entry::getKey, Map.Entry::getValue);
    }

    @Override
    public Mono<Map<String,Integer>> getUserVotedPoll(String email){

        return emailBasedVoterListService
                .getUserVotedPollIds(email)

                .flatMap(pollIds ->
                        getUserVotes(email, new ArrayList<>(pollIds))
                );
    }
}
