package com.danang.TravDana.app.controller;

import com.danang.TravDana.app.request.OtpRequest;
import com.danang.TravDana.app.request.RegisterRequest;
import com.danang.TravDana.app.request.UserRequest;
import com.danang.TravDana.app.service.RegisterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegisterController {

    RegisterService registerService;

    @GetMapping("/dangKi")
    public String dangKi(Model model) {

        model.addAttribute("user", new RegisterRequest());
        return "view/dangKi";
    }

    @PostMapping("/register")
    public String register(Model model,
                           @ModelAttribute("user") RegisterRequest user,
                           HttpServletRequest request) {
        log.info("ok");
        OtpRequest re = registerService.register(user);
        model.addAttribute("email", user.getEmail());
        if(re==null){
            model.addAttribute("user", new RegisterRequest());
            model.addAttribute("message","Vui lòng kiểm tra thông tin");
            return "view/dangKi";
        }else{
            model.addAttribute("otp",re);
            return "view/submitOTP";
        }

    }
    @PostMapping("/checkOTP")
    public String sendOTP(Model model,
                          @ModelAttribute("otp") OtpRequest otpRequest
                          ) {
        OtpRequest op = registerService.checkOtp(otpRequest);
        if(op == null){
            model.addAttribute("message", "Đăng kí thành công");
            model.addAttribute("userRequest", new UserRequest());
            return "view/dangNhap";        }
        model.addAttribute("message", "OTP không chính xác hoặc đã hết hạn");
        return "view/submitOTP";

    }

}
