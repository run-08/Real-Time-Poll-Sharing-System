package com.example.EDA.VoterListModel;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "voter_list")
public class VoterList {
    @MongoId
    private String pollId;
    @Field(name = "voted")
    private Map<String, Integer> voted;
    @Field(name="opions")
    private Set<Integer> options;
    @Override
    public String toString(){
        return "["+" PollId: "+(this.pollId)+", Voted: "+(this.voted).toString()+", "+(this.options)+" ]";
    }
}
