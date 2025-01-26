package com.employe.crud.middleware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

    //retrieve from database.
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails tony = User.builder()
                .username("tony")
                .password("{noop}anakmami")
                .roles("ADMIN", "MANAGER", "EMPLOYEE")
                .build();
        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(tony, mary, susan);
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
        http.authorizeHttpRequests(configurer ->
                configurer.requestMatchers(HttpMethod.GET, "/api/employed").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employed/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employed").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employed/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employed/**").hasRole("ADMIN"));

        //use basic auth
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();

    }
}
