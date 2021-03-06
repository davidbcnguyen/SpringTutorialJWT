package com.example.springtutorialjwt;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ApiUser readUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    public ApiUser createUser(UserCreateRequest userCreateRequest) {
        ApiUser user = new ApiUser();
        Optional<ApiUser> byUsername = userRepository.findByUsername(userCreateRequest.getUsername());
        if (byUsername.isPresent()) {
            throw new RuntimeException("User already registered");
        }
        user.setUsername(userCreateRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        userRepository.save(user);
        return user;
    }

    public ApiUser createFollow(String username, FollowCreateRequest followCreateRequest) {
        Optional<ApiUser> byUsername = userRepository.findByUsername(followCreateRequest.getFollowee_username());
        if (byUsername.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        ApiUser followee = byUsername.get();
        byUsername = userRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        ApiUser follower = byUsername.get();
        follower.getFollowings().add(followee);
        userRepository.save(follower);
        return follower;
    }

    public List<ApiUser> getAll() {
        return userRepository.findAll();
    }
}
