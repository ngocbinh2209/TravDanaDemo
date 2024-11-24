package com.danang.TravDana.app.service.serviceIpm;

import com.danang.TravDana.app.entity.UserEntity;
import com.danang.TravDana.app.repository.UserRepo;
import com.danang.TravDana.app.response.UserBookingResponse;
import com.danang.TravDana.app.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIpm implements UserService {
    private final UserRepo userRepo;

    public UserServiceIpm(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserEntity> getEmployeesByRole(int roleId) {
        return userRepo.findByRole_RoleId(roleId);
    }

    @Override
    public UserBookingResponse getUserBooking(String userName) {
        UserEntity user = userRepo.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        return UserBookingResponse.builder()
                .sdt(user.getPhone())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserEntity findUserById(int userId) {
        return userRepo.findById(userId).orElse(null);  // Trả về null nếu không tìm thấy
    }
}