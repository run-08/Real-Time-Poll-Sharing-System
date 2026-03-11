package com.example.EDA.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollVoteRequestDTO {
    private String pollId;
    private Integer option;
    private String email;
    @Override
    public String toString(){
        return "[ PollId: "+this.getPollId()+", Option: "+this.getOption()+", Email: "+this.getEmail()+" ]";
    }
}
