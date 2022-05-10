package com.example.springtutorialjwt;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public List<MessageDto> getMessages() {
        return messageService.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/feed")
    public List<MessageDto> getFeed(Principal principal) {
        String username = principal.getName();
        return messageService.getFeed(username).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private MessageDto convertToDto(Message message) {
        MessageDto messageDto = modelMapper.map(message, MessageDto.class);
        messageDto.setUsername(message.getUser().getUsername());
        return messageDto;
    }
}
