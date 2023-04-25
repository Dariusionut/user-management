package com.spring.studentmanagement.security;

import com.spring.studentmanagement.security.interfaces.AppAuthManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created at 4/25/2023 by Darius
 **/
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpls userDetailsServiceImpls;
    private final HttpServletRequest request;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AppAuthManager authenticationManager(){
        return new AppAuthenticationManagerImpl(this.userDetailsServiceImpls, this.passwordEncoder(), this.request);
    }
}
