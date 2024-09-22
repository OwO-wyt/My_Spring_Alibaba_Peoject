package com.www.security.service;

import com.www.security.entity.SecurityUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public Mono<UserDetails> findByUsername(String s) {
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(
                "user", passwordEncoder.encode("user"),
                true, true, true, true, new ArrayList<>());

        return Mono.just(securityUserDetails);
    }
}
