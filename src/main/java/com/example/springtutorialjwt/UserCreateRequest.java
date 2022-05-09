package com.example.springtutorialjwt;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String username;
    private String password;
}
