package com.example.EDA.Service;

import com.example.EDA.DTO.PollVoteRequestDTO;
import com.example.EDA.Exceptions.InvalidOptionException;
import com.example.EDA.Exceptions.VoterListPollIDExistException;
import com.example.EDA.Repository.VoterListRepo;
import com.example.EDA.VoterListModel.VoterList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoterListServiceImpl implements VoterListService {

    private final VoterListRepo voterListRepo;

    @Override
    public Mono<VoterList> savePoll(VoterList voterList) {
        log.info("VoterList: ",voterList.toString());
        return voterListRepo.save(voterList);
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
        email = email.replace(".","_");
        if(!isPollExist(pollId)){
            return Mono.error(new VoterListPollIDExistException(pollId));
        }
        String finalEmail = email;
        return
               voterListRepo
                       .findById(pollId)
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
    public boolean isPollExist(String pollId) {
        log.info("Service for identifying if id exist or not: "+(pollId));
        Mono<VoterList> voterListMono =
                voterListRepo.findById(pollId);
        return voterListMono != null;
    }
}
