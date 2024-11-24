package com.danang.TravDana.app.controller.rest;

import com.danang.TravDana.app.exception.UserNotFoundException;
import com.danang.TravDana.app.request.UserRequest;
import com.danang.TravDana.app.service.LoginService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("test")
public class login {
    LoginService loginService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/testlogin")
    public Object login(@RequestBody UserRequest userRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getUserName(), userRequest.getPassword())
            );

            // Lưu tt user vào SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return authentication.getPrincipal();
        } catch (Exception e) {
            throw new UserNotFoundException();
        }

    }
}
