package com.danang.TravDana.auth;
import com.danang.TravDana.app.entity.UserEntity;
import com.danang.TravDana.app.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userRepo.findByUserName(username)
                .orElseThrow(()->
                        new UsernameNotFoundException(username)
                );
        System.out.println("User:" + user);
        List<GrantedAuthority> authorityList =
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName()));

        System.out.println("authorityList:"+authorityList);
        return new User(user.getUserName(), user.getPassword(), authorityList);
    }
}
