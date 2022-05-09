package com.example.springtutorialjwt;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApiUser, Long> {
    Optional<ApiUser> findByUsername(String username);
}
