package com.example.springtutorialjwt;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDtoWithFollowings extends UserDto {
    private List<UserDto> followings;
}
