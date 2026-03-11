package com.example.EDA.Service;

import com.example.EDA.DTO.PollVoteRequestDTO;
import com.example.EDA.Exceptions.InvalidOptionException;
import com.example.EDA.Exceptions.VoterListPollIDExistException;
import com.example.EDA.VoterListModel.VoterList;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface VoterListService {
    public Mono<VoterList> savePoll(VoterList voterList);
    public Flux<VoterList> getAllPolls();
    public Mono<VoterList> votePoll(PollVoteRequestDTO pollVoteRequestDTO) throws InvalidOptionException, VoterListPollIDExistException;
    public boolean isPollExist(String pollId);
}
