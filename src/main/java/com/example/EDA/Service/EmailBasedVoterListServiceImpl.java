package com.example.EDA.Service;

import com.example.EDA.DTO.EmailBasedVoterListRequestDTO;
import com.example.EDA.Repository.EmailBasedVoterListRepo;
import com.example.EDA.VoterListModel.EmailBasedVoterList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailBasedVoterListServiceImpl implements EmailBasedVoterListService{

    private final EmailBasedVoterListRepo emailBasedVoterListRepo;
    @Override
    public Mono<EmailBasedVoterList> saveUserVote(EmailBasedVoterListRequestDTO emailBasedVoterListRequestDTO) {
         String email = emailBasedVoterListRequestDTO.getEmailId();
         String pollId = emailBasedVoterListRequestDTO.getPollId();
         log.info("EmailBasedVoterListRequestDTO: {}"+emailBasedVoterListRequestDTO);
        Mono<EmailBasedVoterList> emailBasedVoterListMono = emailBasedVoterListRepo
                .findByEmailId(email);
         emailBasedVoterListMono
                .map(emailBasedVoterList -> {
                    log.info("EmailBasedVoterList: "+emailBasedVoterList);
                    return emailBasedVoterList;
                });
        Mono<EmailBasedVoterList> result =
                emailBasedVoterListRepo
                .findById(email)
                .flatMap(emailBasedVoterList -> {
                    System.out.println(emailBasedVoterList);
                    emailBasedVoterList.getUserVotedPollIds().add(pollId);
                    return emailBasedVoterListRepo.save(emailBasedVoterList);
                })
                .switchIfEmpty(
                        emailBasedVoterListRepo.save(
                                new EmailBasedVoterList(
                                        email,
                                        new HashSet<>(Set.of(pollId))
                                )
                        )
                );
        result.subscribe();
        return result;
    }
    @Override
    public Mono<EmailBasedVoterList> deleteEmailBasedUserVote(String emailId, String pollId) {
        log.info("EmailBased Service: "+(emailId)+" pollId: "+(pollId));
        Mono<EmailBasedVoterList> result =  emailBasedVoterListRepo
                .findById(emailId)
                .flatMap(emailBasedVoterList -> {
                    emailBasedVoterList.getUserVotedPollIds().remove(pollId);
                    return emailBasedVoterListRepo.save(emailBasedVoterList);
                });
        result.subscribe();
        return result;
    }
    @Override
    public Mono<Set<String>> getUserVotedPollIds(String email) {
        return emailBasedVoterListRepo
                .findById(email)
                .map(EmailBasedVoterList::getUserVotedPollIds)
                .defaultIfEmpty(Set.of());
    }
}
