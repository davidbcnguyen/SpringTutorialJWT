package com.example.springtutorialjwt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM message m WHERE user_id IN (SELECT followee_id FROM following WHERE follower_id = (SELECT id FROM user WHERE username = ?1))")
    List<Message> getFollowingMessages(String username);
}
