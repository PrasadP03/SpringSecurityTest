package com.spring.security.test.SpringSecurityTest.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class securityconfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails normaluser= User.withUsername("alpha").
                password(passwordEncoder().encode("alpha1")).
                roles("NORMAL")
                .build();
        UserDetails adminuser=User.withUsername("beta")
                .password(passwordEncoder().encode("beta1"))
                .roles("ADMIN")
                .build();
        InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager(normaluser,adminuser);
        return inMemoryUserDetailsManager;
    }
    @Bean
    public SecurityFilterChain filterchain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf ->csrf.disable())
                .authorizeHttpRequests(auth->auth
                        //.requestMatchers("home/admin").hasRole("ADMIN")
                        //.requestMatchers("home/normal").hasRole("NORMAL")
                        .requestMatchers("/home/public").permitAll().anyRequest().
                        authenticated()
                )
                .formLogin(withDefaults());


        return httpSecurity.build();
    }
}
