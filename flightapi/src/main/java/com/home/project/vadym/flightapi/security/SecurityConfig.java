package com.home.project.vadym.flightapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web
        .configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web
        .configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*        auth
            .jdbcAuthentication()
            .dataSource(dataSource)
                .withUser("buzz")
                    .password("infinity")
                    .authorities("ROLE_USER")
            .and()
                .withUser("woody")
                    .password("bullseye")
                    .authorities("ROLE_USER");*/
/*        auth
            .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());*/




    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http
            .authorizeRequests()
//                .antMatchers("/api/flights").denyAll()
//                .hasRole("USER")
            .antMatchers("/registration").permitAll();*/
    }

}
