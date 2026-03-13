package com.example.EDA.Repository;

import com.example.EDA.VoterListModel.VoterList;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface VoterListRepo extends ReactiveMongoRepository<VoterList,String> {
    Mono<VoterList> findByPollId(String PollId);
    Mono<VoterList> deleteByPollId(String pollId);
}
