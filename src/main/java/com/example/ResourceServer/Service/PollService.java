package com.example.ResourceServer.Service;

import com.example.ResourceServer.DTO.PollDTO;
import com.example.ResourceServer.Exception.PollIDExistException;
import com.example.ResourceServer.Exception.PollIDNotFoundedException;
import com.example.ResourceServer.Model.Poll;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PollService {
    public PollDTO savePoll(PollDTO pollDTO) throws PollIDExistException;
    public PollDTO deletePoll(String pollId) throws PollIDNotFoundedException;
    public Poll DTOToEntity(PollDTO pollDTO);
    public boolean isIdPollExist(String pollId);
    public PollDTO EntityToDTO(Poll poll);
    public List<PollDTO> getPolls(String emailId);
    public List<PollDTO> getUserPoll(String emailId);
    public void deleteUser(String emailId);
    public Integer getUserPollCount(String email);

}
