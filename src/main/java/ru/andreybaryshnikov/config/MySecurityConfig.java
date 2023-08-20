package ru.andreybaryshnikov.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

//@Configuration
//@EnableWebSecurity
//public class MySecurityConfig {
//    @Autowired
//    DataSource dataSource;
//
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        return users;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((user) -> user
//            .requestMatchers(new AntPathRequestMatcher("/")).hasAnyRole("HR", "MANAGER", "IT", "EMPLOYEE")
//            .requestMatchers(new AntPathRequestMatcher("/manager_info/**")).hasRole("MANAGER")
//            .requestMatchers(new AntPathRequestMatcher("/hr_info/**")).hasRole("HR")
//            .anyRequest().authenticated()
//        ).formLogin(Customizer.withDefaults());
//        return http.build();
//    }
//
//}
