package com.example.springtutorialjwt;

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
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping
    public UserDto createUser(@RequestBody UserCreateRequest userCreateRequest) {
        ApiUser createdUser = userService.createUser(userCreateRequest);
        return convertToDto(createdUser);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserDto convertToDto(ApiUser user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
