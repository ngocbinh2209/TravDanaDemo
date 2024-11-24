package com.danang.TravDana.app.controller;

import com.danang.TravDana.app.entity.UserEntity;
import com.danang.TravDana.app.repository.UserRepo;
import com.danang.TravDana.app.request.UserRequest;
import com.danang.TravDana.app.service.LoginService;
import com.danang.TravDana.auth.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginController {

    LoginService loginService;
    AuthenticationManager authenticationManager;
    SessionService sessionService;
    UserRepo userRepo;

    @GetMapping("/dangNhap")
    public String dangNhap(Model model, @RequestParam("error") Optional<String> error) {
        if(error.isPresent()) {
            model.addAttribute("message", "Vui lòng đăng nhập!");
        }
        model.addAttribute("userRequest", new UserRequest());
        return "view/dangNhap";
    }

    @PostMapping("/checkLogin")
    public String login(Model model,
                        HttpServletRequest request,
                        @ModelAttribute("userRequest") UserRequest userRequest
    ) {
        UserEntity user = userRepo.findByUserName(userRequest.getUserName())
                .orElseThrow(()->
                        new UsernameNotFoundException(userRequest.getUserName())
                );

        System.out.println("User:" + user);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getUserName(), userRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            sessionService.saveUserToSession(request, authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute("fullname", user.getFullName());

            System.out.println("Current Authentication after setting: " + SecurityContextHolder.getContext());
            System.out.println(authentication.isAuthenticated());

            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) ||
                    authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STAFF"))){
                return "redirect:/indexManage";
            }else{
                return "redirect:/";
            }
        } catch (
                Exception e) {
            log.info("Login failed!");
            log.error(e.getMessage(), e);
            model.addAttribute("message", "Đăng nhập thất bại");
            return "view/dangNhap";
        }

    }

    @GetMapping("/dangXuat")
    public String dangXuat(Model model, HttpServletRequest request) {
        sessionService.clearSession(request);
        return "view/dangNhap";
    }

    @GetMapping("/error/403")
    public String error403() {
        return "view/error";
    }
}




