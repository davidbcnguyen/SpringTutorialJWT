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

    @PostMapping(path = "/follow")
    public UserDto createFollow(Principal principal, @RequestBody FollowCreateRequest followCreateRequest) {
        String username = principal.getName();
        ApiUser follower = userService.createFollow(username, followCreateRequest);
        return convertToDto(follower);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserDto convertToDto(ApiUser user) {
        UserDto userDto = modelMapper.map(user, UserDtoWithFollowings.class);
        return userDto;
    }
}
