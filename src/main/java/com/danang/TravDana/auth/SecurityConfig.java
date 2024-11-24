package com.danang.TravDana.auth;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig {

    UserDetailsService userDetailsService;

    final String[] publicEndPoint = {"/", "/tourDetail", "/tourList", "/diaDiem", "/lienHe",
            "/dangNhap", "/dangKi", "/sendOTP", "/checkOTP","register", "/thuVienAnh", "/checkLogin",
            "/css/**", "/js/**", "/images/**", "/resources/**", "manage_TravDana/assets/**",
            "manage_TravDana/assets/js/**", "/test/**", "/view/**",
            "/component/**", "/detail_component/**"};
    final String[] adminEndpoint = {"/indexManage"};
    final String[] manageEndpoint = {"/demo"};
    final String[] userEndpoint = {"/user/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicEndPoint)
                        .permitAll()
                        .requestMatchers(adminEndpoint)
                        .hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers(userEndpoint)
                        .hasAnyRole("CLIENT")
                        .requestMatchers(manageEndpoint)
                        .hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )
                .logout(logout -> logout
                        .logoutUrl("/dangXuat")
                        .logoutSuccessUrl("/dangNhap")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .formLogin(login -> login
                        .loginPage("/dangNhap")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/", true)
                ).exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())
                );
        ;
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.sendRedirect("/dangNhap?error=loginfailed");
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/error/403");
        };
    }


}
