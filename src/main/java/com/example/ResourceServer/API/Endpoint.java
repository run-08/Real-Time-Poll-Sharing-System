package com.example.ResourceServer.API;

import com.example.ResourceServer.DTO.PollDTO;
import com.example.ResourceServer.Exception.PollIDExistException;
import com.example.ResourceServer.Exception.PollIDNotFoundedException;
import com.example.ResourceServer.Service.PollServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@EnableDiscoveryClient
public class Endpoint {

    private final PollServiceImpl pollService;

    @PostMapping("/savePoll")
    public ResponseEntity<PollDTO> savePoll(@RequestBody PollDTO pollDTO) throws PollIDExistException {
        log.info("User Poll: "+(pollDTO)+"\n Date And Time: "+(LocalDateTime.now()));
        pollDTO = pollService.savePoll(pollDTO);
        return ResponseEntity.ok(pollDTO);
    }

    @DeleteMapping("/deletePoll")
    public ResponseEntity<PollDTO> deletePoll(@RequestParam String pollID) throws PollIDNotFoundedException {
        PollDTO pollDTO = pollService.deletePoll(pollID.trim());
        log.info("Poll id "+(pollID)+"removed from Databases!");
        return ResponseEntity.ok(pollDTO);
    }

    @GetMapping("/getPolls")
    public ResponseEntity<List<PollDTO>> getPolls(@RequestParam String pollID){
        List<PollDTO> pollDTOs  = pollService.getPolls(pollID);
        return ResponseEntity.ok(pollDTOs);
    }

    @GetMapping("/getUserPoll")
    public ResponseEntity<List<PollDTO>> getUserPoll(@RequestParam String email){
        List<PollDTO> pollDTOs  = pollService.getUserPoll(email);
        return ResponseEntity.ok(pollDTOs);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam String email){
         pollService.deleteUser(email);
         return ResponseEntity.ok("User poll deleted Successfully!");
    }

    @GetMapping("/getUserPollCount")
    public ResponseEntity<Integer> getUserPollCount(@RequestParam String email){
       return
               ResponseEntity.ok(pollService.getUserPollCount(email));
    }

}
