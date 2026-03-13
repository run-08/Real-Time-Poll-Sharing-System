package com.example.EDA.RequestHandler;

import com.example.EDA.DTO.PollVoteRequestDTO;
import com.example.EDA.DTO.VoterListDTO;
import com.example.EDA.Exceptions.InValidEmailVoterListException;
import com.example.EDA.Exceptions.InvalidOptionException;
import com.example.EDA.Exceptions.VoterListException;
import com.example.EDA.Exceptions.VoterListPollIDExistException;
import com.example.EDA.Service.VoterListServiceImpl;
import com.example.EDA.VoterListModel.VoterList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.example.EDA.DTO.PollCheckRequestDTO;

import java.util.List;

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

    public Mono<ServerResponse> deletePollById(ServerRequest serverRequest){
        log.info("Request gotten: "+serverRequest.toString());
        String pollId  = serverRequest.queryParam("PollId").orElse(null);
        String email = serverRequest.queryParam("emailId").orElse(null);
        if(email == null){
            return Mono.error(new InValidEmailVoterListException(email));
        }
        if(pollId == null){
            return Mono.error(new VoterListPollIDExistException(pollId));
        }
        return ServerResponse
                .ok()
                .body(voterListService.deleteVoterList(pollId,email),VoterList.class);
    }

    public Mono<ServerResponse> isPollIdsExist(ServerRequest serverRequest){
        log.info("Request gotten: "+(serverRequest.toString()));
        return serverRequest.bodyToMono(PollCheckRequestDTO.class)

                .flatMap(req ->
                        voterListService.getUserVotes(req.getEmail(), req.getPollId())
                )
                .flatMap(result ->
                        ServerResponse.ok().bodyValue(result)
                );
    }

    public Mono<ServerResponse> getUserVotedPoll(ServerRequest serverRequest){
        return serverRequest.bodyToMono(PollCheckRequestDTO.class)

                .flatMap(req ->
                        voterListService.getUserVotedPoll(req.getEmail())
                )
                .flatMap(result ->
                        ServerResponse.ok().bodyValue(result)
                );
    }
}
