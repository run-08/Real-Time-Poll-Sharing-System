package com.example.ResourceServer.DTO;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PollDTO {
    @NotNull
    private String id;
    @NotNull
    private String question;
    @NotNull
    private String options;
    @NotNull
    private String emailId;
    @NotNull
    private LocalDateTime time;
}
