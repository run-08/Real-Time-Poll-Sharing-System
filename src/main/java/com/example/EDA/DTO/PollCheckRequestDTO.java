package com.example.EDA.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollCheckRequestDTO {
    private String email;
    private List<String> pollId;
}
