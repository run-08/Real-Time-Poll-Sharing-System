package com.example.EDA.Service;

import com.example.EDA.DTO.EmailBasedVoterListRequestDTO;
import com.example.EDA.VoterListModel.EmailBasedVoterList;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public interface EmailBasedVoterListService {
    public Mono<EmailBasedVoterList> saveUserVote(EmailBasedVoterListRequestDTO emailBasedVoterListRequestDTO);
    public Mono<EmailBasedVoterList> deleteEmailBasedUserVote(String emailId, String pollId);
    public Mono<Set<String>> getUserVotedPollIds(String email);

}
