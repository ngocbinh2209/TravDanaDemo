package com.danang.TravDana.app.request;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class RegisterRequest {

    private String userName;

    private String password;

    private String fullName;

    private String phone;

    private String email;

    private LocalDate birthday;

    private boolean gender;



}
