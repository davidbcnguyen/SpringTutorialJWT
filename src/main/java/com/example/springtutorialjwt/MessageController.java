package com.example.springtutorialjwt;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    @PostMapping
    public MessageDto createMessage(Principal principal, @RequestBody MessageCreateRequest messageCreateRequest) {
        String username = principal.getName();
        Message createdMessage = messageService.createMessage(username, messageCreateRequest);
        return convertToDto(createdMessage);
    }

    private MessageDto convertToDto(Message message) {
        MessageDto messageDto = modelMapper.map(message, MessageDto.class);
        return messageDto;
    }
}
