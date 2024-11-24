package com.danang.TravDana.app.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class UserResponse {

    private int userId;

    private String userName;

    private String password;

    private String fullName;

    private String phone;

    private String email;

    private LocalDate birthday;

    private String status;

    private boolean gender;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String role;
}
