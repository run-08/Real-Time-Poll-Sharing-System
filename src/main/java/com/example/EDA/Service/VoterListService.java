package com.example.EDA.Service;

import com.example.EDA.VoterListModel.VoterList;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface VoterListService {
    public Mono<VoterList> savePoll(VoterList voterList);
    public Flux<VoterList> getAllPolls();
}
