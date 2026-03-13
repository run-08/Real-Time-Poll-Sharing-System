package com.example.EDA.VoterListModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "EmailBasedVoterList")
public class EmailBasedVoterList {
    @Id
    private String emailId;
    private Set<String> userVotedPollIds;
}
