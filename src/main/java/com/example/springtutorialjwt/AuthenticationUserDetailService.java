package com.example.springtutorialjwt;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApiUser apiUser = userService.readUserByUsername(username);
        if (apiUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(apiUser.getUsername(), apiUser.getPassword(), Collections.emptyList());
    }
}
