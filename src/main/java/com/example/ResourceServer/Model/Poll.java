package com.example.ResourceServer.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Poll {
    @Id
    private String id;
    @Column(name = "question",nullable = false)
    private String question;
    @Column(name = "options",nullable = false,columnDefinition = "varchar(1200)")
    private String options;
    @Column(name = "emailId",nullable = false)
    private String emailId;
    private LocalDateTime time;
    private AtomicInteger no_of_votes;
}
