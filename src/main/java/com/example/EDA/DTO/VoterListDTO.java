package com.example.EDA.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoterListDTO {
    private String pollId;
    private Map<String, Integer> voted;
    private Set<Integer> options;
}
