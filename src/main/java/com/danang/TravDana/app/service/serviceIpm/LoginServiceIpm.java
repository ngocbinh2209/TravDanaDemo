package com.danang.TravDana.app.service.serviceIpm;

import com.danang.TravDana.app.entity.UserEntity;
import com.danang.TravDana.app.exception.InvalidRequestException;
import com.danang.TravDana.app.repository.UserRepo;
import com.danang.TravDana.app.request.UserRequest;
import com.danang.TravDana.app.response.UserResponse;
import com.danang.TravDana.app.service.LoginService;
import com.danang.TravDana.app.utils.SendMailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginServiceIpm implements LoginService {

    UserRepo userRepo;
    private final SendMailService sendMailService;

    @Override
    public UserResponse login(UserRequest userRequest) {

        if (userRequest.getUserName().equals("") || userRequest.getPassword().equals("")) {
            throw new InvalidRequestException();
        }
        UserEntity user = userRepo
                .findByUserName(userRequest.getUserName())
                .orElseThrow(
                        () -> new InvalidRequestException()
                );
        System.out.println("userName: "+ user.getUserName());
        return this.doLogin(userRequest, user);

    }

    private UserResponse doLogin (UserRequest userRequest, UserEntity user) {
        if(!user.getPassword().equals(userRequest.getPassword())) {

            return null;
        }
        System.out.println("userName: "+ user.getUserName());
        System.out.println("userName: "+ user.getRole().getRoleName());
//        customUserDetailsService.loadUserByUsername(user.getUserName());

    return UserResponse.builder()
            .userId(user.getUserId())
            .userName(user.getUserName())
            .password(user.getPassword())
            .fullName(user.getFullName())
            .phone(user.getPhone())
            .email(user.getEmail())
            .birthday(user.getBirthday())
            .gender(user.isGender())
            .createdAt(user.getCreatedAt())
            .role(user.getRole().getRoleName())
            .build();
    }


}
