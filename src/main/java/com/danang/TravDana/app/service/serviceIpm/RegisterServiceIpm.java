package com.danang.TravDana.app.service.serviceIpm;

import com.danang.TravDana.app.entity.RoleEntity;
import com.danang.TravDana.app.entity.UserEntity;
import com.danang.TravDana.app.exception.InvalidRequestException;
import com.danang.TravDana.app.repository.RoleRepo;
import com.danang.TravDana.app.repository.UserRepo;
import com.danang.TravDana.app.request.OtpRequest;
import com.danang.TravDana.app.request.RegisterRequest;
import com.danang.TravDana.app.service.RegisterService;
import com.danang.TravDana.app.utils.SendMailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegisterServiceIpm implements RegisterService {

    UserRepo userRepo;
    SendMailService sendMailService;
    RoleRepo roleRepo;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public OtpRequest register(RegisterRequest registerRequest) {
        UserEntity user;
        RoleEntity role = roleRepo.findById(3).orElseThrow();

        String otp = this.generateOtp();
        String ecOTP = this.encodeOtp(otp);

        UserEntity userConvert = new UserEntity();
        userConvert.setUserName(registerRequest.getUserName());
        userConvert.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userConvert.setFullName(registerRequest.getFullName());
        userConvert.setPhone(registerRequest.getPhone());
        userConvert.setEmail(registerRequest.getEmail());
        userConvert.setBirthday(registerRequest.getBirthday());
        userConvert.setStatus("CHUA_XAC_NHAN");
        userConvert.setGender(registerRequest.isGender());
        userConvert.setCreatedAt(LocalDateTime.now());
        userConvert.setUpdatedAt(LocalDateTime.now());
        userConvert.setOTP(ecOTP);
        userConvert.setOtpTime(LocalDateTime.now().plus(60, ChronoUnit.SECONDS));
        userConvert.setUpdatedAt(LocalDateTime.now());
        userConvert.setRole(role);


        try {
            if(userRepo.findByUserName(registerRequest.getUserName()).isPresent()){
                return null;
            }


            user = userRepo.save(userConvert);
            this.sendOTP(registerRequest.getEmail(), otp);
        } catch (Exception e) {
            throw new InvalidRequestException();
        }
        if (user == null) {
            return null;
        }
        return OtpRequest.builder()
                .email(user.getEmail())
                .build();

    }


    private void sendOTP(String email, String otp) {
        log.info(otp);
        sendMailService.sendOTPtoUser(email, otp);

    }


    @Override
    public OtpRequest checkOtp(OtpRequest otpRequest) {
        UserEntity user = userRepo.findByEmail(otpRequest.getEmail()).orElseThrow();
        System.out.println(LocalDateTime.now());
        System.out.println(user.getOtpTime());
        if(otpRequest.getOtp().equals(this.decodeOtp(user.getOTP())) &&
                LocalDateTime.now().isBefore(user.getOtpTime())){
            return null;
        }

        return OtpRequest.builder()
                .email(user.getEmail())
                .build();

    }


    private String generateOtp() {
        Random random = new Random();
        String otp = String.valueOf(100000 + random.nextInt(900000));
        return otp;
    }

    public String encodeOtp(String otp) {
        return Base64.getEncoder().encodeToString(otp.getBytes());
    }

    public String decodeOtp(String encodedOtp) {
        return new String(Base64.getDecoder().decode(encodedOtp));
    }

}
