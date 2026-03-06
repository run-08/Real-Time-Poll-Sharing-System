package com.example.ResourceServer.Repository;

import com.example.ResourceServer.Model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PollRepo extends JpaRepository<Poll,String> {
    public List<Poll> findByEmailId(String emailId);

    @Modifying
    @Transactional
    @Query("Delete from Poll p where p.emailId =:email")
    public void deleteUserPoll(@Param("email") String email);
}
