package com.danang.TravDana.app.service;

import com.danang.TravDana.app.request.OtpRequest;
import com.danang.TravDana.app.request.RegisterRequest;
import com.danang.TravDana.app.request.UserRequest;
import com.danang.TravDana.app.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface RegisterService {

    OtpRequest register(RegisterRequest registerRequest);

    OtpRequest checkOtp(OtpRequest otpRequest);
}
