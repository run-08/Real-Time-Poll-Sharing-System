package com.example.EDA.Service;

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
}
