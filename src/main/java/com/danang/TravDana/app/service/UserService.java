package com.danang.TravDana.app.service;

import com.danang.TravDana.app.entity.UserEntity;
import com.danang.TravDana.app.response.UserBookingResponse;

import java.util.List;

public interface UserService {
    List<UserEntity> getEmployeesByRole(int roleId);

    UserBookingResponse getUserBooking(String userName);

    UserEntity findUserById(int userId);
}