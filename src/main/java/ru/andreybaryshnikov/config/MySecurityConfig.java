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

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
    DataSource dataSource;

    public MySecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((user) -> user
            .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/viewRecord")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/deleteRecord")).hasRole("ADMIN")
            .requestMatchers(new AntPathRequestMatcher("/editRecord")).hasRole("ADMIN")
            .requestMatchers(new AntPathRequestMatcher("/editSaveRecord")).hasRole("ADMIN")
            .requestMatchers(new AntPathRequestMatcher("/viewAddRecord")).hasRole("ADMIN")
            .requestMatchers(new AntPathRequestMatcher("/addSaveRecord")).hasRole("ADMIN")
//            .requestMatchers(new AntPathRequestMatcher("/deleteRecord/**")).hasRole("MANAGER")
//            .requestMatchers(new AntPathRequestMatcher("/editRecord/**")).hasRole("HR")
            .anyRequest().authenticated())
            .formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout((logout) -> logout.logoutSuccessUrl("/").permitAll());
        return http.build();
    }

}
