package com.example.EDA.Repository;

import com.example.EDA.VoterListModel.EmailBasedVoterList;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EmailBasedVoterListRepo extends ReactiveMongoRepository<EmailBasedVoterList,String> {
    Mono<EmailBasedVoterList> findByEmailId(String email);
}
