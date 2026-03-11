package com.example.EDA.RequestHandler;

import com.example.EDA.DTO.PollVoteRequestDTO;
import com.example.EDA.DTO.VoterListDTO;
import com.example.EDA.Exceptions.InvalidOptionException;
import com.example.EDA.Exceptions.VoterListException;
import com.example.EDA.Exceptions.VoterListPollIDExistException;
import com.example.EDA.Service.VoterListServiceImpl;
import com.example.EDA.VoterListModel.VoterList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class PollRequestHandler {
    private final VoterListServiceImpl voterListService;
    public Mono<ServerResponse> savePoll(ServerRequest serverRequest){
        log.info("Request Info: "+serverRequest.toString());
        return
                serverRequest.bodyToMono(VoterListDTO.class)
                        .map(dto -> VoterList.builder()
                                .pollId(dto.getPollId())
                                .voted(dto.getVoted())
                                .options(dto.getOptions())
                                .build())
                        .flatMap(voterListService::savePoll)
                        .flatMap(voterList -> ServerResponse.ok().bodyValue(voterList))
                        .onErrorResume(VoterListException.class,ex -> ServerResponse.badRequest().bodyValue(ex.getMessage()))
                        .onErrorResume(ex -> ServerResponse.status(500).bodyValue(ex));
    }

    public Mono<ServerResponse> getAllPolls(ServerRequest serverRequest){
        log.info("Request Info: "+serverRequest.toString());
        return
                ServerResponse
                        .ok()
                        .body(voterListService.getAllPolls(), VoterList.class)
                        .onErrorResume(VoterListException.class, ex -> ServerResponse.badRequest().bodyValue(ex.getMessage()))
                        .onErrorResume(ex -> ServerResponse.status(500).bodyValue(ex));
    }

    public Mono<ServerResponse> votePoll(ServerRequest serverRequest){
        log.info("Request gotten: "+serverRequest.toString());
        return
                serverRequest.bodyToMono(PollVoteRequestDTO.class)
                        .flatMap(voterListService::votePoll)
                        .flatMap(voterList -> ServerResponse.ok().bodyValue(voterList))
                        .onErrorResume(InvalidOptionException.class,
                                ex -> ServerResponse.badRequest().bodyValue(ex.getMessage()))

                        .onErrorResume(VoterListPollIDExistException.class,
                                ex -> ServerResponse.badRequest().bodyValue(ex.getMessage()))
                        .onErrorResume(ex ->  ServerResponse.status(500).bodyValue(ex.getMessage()));
    }
}
