package com.example.EDA.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailBasedVoterListRequestDTO {
    private String emailId;
    private String pollId;
}
