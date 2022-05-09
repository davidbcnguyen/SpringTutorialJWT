package com.example.springtutorialjwt;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private List<MessageDto> message;
}
