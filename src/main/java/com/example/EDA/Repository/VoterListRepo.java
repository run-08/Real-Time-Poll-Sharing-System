package com.example.EDA.Repository;

import com.example.EDA.VoterListModel.VoterList;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VoterListRepo extends ReactiveMongoRepository<VoterList,Integer> {

}
