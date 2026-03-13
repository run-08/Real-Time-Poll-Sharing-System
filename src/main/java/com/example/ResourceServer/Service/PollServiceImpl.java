package com.example.ResourceServer.Service;

import com.example.ResourceServer.DTO.PollDTO;
import com.example.ResourceServer.Exception.PollIDExistException;
import com.example.ResourceServer.Exception.PollIDNotFoundedException;
import com.example.ResourceServer.Model.Poll;
import com.example.ResourceServer.Repository.PollRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PollServiceImpl implements PollService{

    private final PollRepo repo;
    @Override
    public PollDTO savePoll(PollDTO pollDTO) throws PollIDExistException {
        System.out.println(pollDTO);
        Poll poll = DTOToEntity(pollDTO);
        pollDTO.setTime(LocalDateTime.now());
        if(isIdPollExist(poll.getId())){
            throw new PollIDExistException(poll.getId());
        }
        repo.save(poll);
        return pollDTO;
    }

    @Override
    public PollDTO deletePoll(String pollId) throws PollIDNotFoundedException {
        if(!isIdPollExist(pollId)){
            throw new PollIDNotFoundedException(pollId);
        }
        Poll poll = repo.findById(pollId).get();
        repo.deleteById(pollId);
        return EntityToDTO(poll);
    }

    @Override
    public Poll DTOToEntity(PollDTO pollDTO){
        return Poll
                .builder()
                .id(pollDTO.getId())
                .options(pollDTO.getOptions())
                .question(pollDTO.getQuestion())
                .emailId(pollDTO.getEmailId())
                .time(LocalDateTime.now())
                .build();
    }

    @Override
    public boolean isIdPollExist(String pollId) {
        Optional<Poll> poll = repo.findById(pollId);
        return poll.isPresent();
    }

    @Override
    public PollDTO EntityToDTO(Poll poll) {
        return PollDTO
                .builder()
                .question(poll.getQuestion())
                .emailId(poll.getEmailId())
                .options(poll.getOptions())
                .id(poll.getId())
                .time(poll.getTime())
                .build();
    }

    @Override
    public List<PollDTO> getPolls(String emailId) {
        List<Poll> polls = repo.findAll();
        return polls
                .stream()
                .filter(poll -> !(poll.getEmailId().equals(emailId)))
                .map(this::EntityToDTO)
                .toList();
    }


    @Override
    public List<PollDTO> getUserPoll(String emailId) {
        List<Poll> polls = repo.findAll();
        System.out.println(polls);
        return polls
                .stream()
                .filter(poll -> poll.getEmailId().equals(emailId))
                .map(this::EntityToDTO)
                .toList();
    }



    @Override
    public void deleteUser(String emailId) {
        repo
                .deleteUserPoll(emailId);
    }

    @Override
    public Integer getUserPollCount(String email){
        return getUserPoll(email).size();
    }
}
