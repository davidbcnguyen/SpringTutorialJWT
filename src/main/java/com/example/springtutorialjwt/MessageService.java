package com.example.springtutorialjwt;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public Message createMessage(String username, MessageCreateRequest messageCreateRequest) {
        Optional<ApiUser> byUsername = userRepository.findByUsername(username);
        if (!byUsername.isPresent()) {
            throw new RuntimeException("User doesn't exist");
        }
        Message message = new Message();
        message.setBody(messageCreateRequest.getBody());
        message.setUser(byUsername.get());
        messageRepository.save(message);
        return message;
    }

    public List<Message> getAll() {
        return messageRepository.findAll();
    }
}
