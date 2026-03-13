package com.example.EDA.Service;

import com.example.EDA.DTO.PollVoteRequestDTO;
import com.example.EDA.Exceptions.InvalidOptionException;
import com.example.EDA.Exceptions.VoterListPollIDExistException;
import com.example.EDA.VoterListModel.VoterList;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public interface VoterListService {
    public Mono<VoterList> savePoll(VoterList voterList);
    public Flux<VoterList> getAllPolls();
    public Mono<VoterList> votePoll(PollVoteRequestDTO pollVoteRequestDTO) throws InvalidOptionException, VoterListPollIDExistException;
    public Mono<Boolean> isPollExist(String pollId);
    public Mono<VoterList> deleteVoterList(String pollId,String emailId);
    public Mono<Map<String,Integer>> getUserVotes(String email, List<String> pollIds);
    public Mono<Map<String,Integer>> getUserVotedPoll(String email);

}
