package com.project.thymeleafcrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    //    add security configuration here...

    @Bean
    public InMemoryUserDetailsManager userManager() {
        UserDetails yash = User.builder()
                .username("yash")
                .password("{noop}yash111")
                .roles("EMPLOYEE")
                .build();


        UserDetails akshay = User.builder()
                .username("akshay")
                .password("{noop}akshay111")
                .roles("EMPLOYEE", "MANAGER")
                .build();


        UserDetails shivam = User.builder()
                .username("shivam")
                .password("{noop}shivam111")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();


        return new InMemoryUserDetailsManager(shivam, akshay, yash);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // any request to the app must be authenticated (logged in)
                .authorizeHttpRequests(configurer ->
                        configurer
                                // ** means all subdirectories recursively
                                .requestMatchers(HttpMethod.GET, "/employees/list").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/employees/showFormForAdd/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET, "/employees/showFromForUpdate/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET, "/employees/deleteEmployee/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                // for customization login form
                .formLogin(form ->
                        form
                                // This is Controller Page
                                .loginPage("/LoginPage")
                                // submit the form data to predefine login processor
                                .loginProcessingUrl("/authenticateTheUser")
                                // everyone can access that form without login
                                .permitAll()
                )
                // for custom logout button
                .logout(logout -> logout.permitAll())
                // Redirect to Custom Access Denied Page
                .exceptionHandling(configurer ->
                        configurer
                                // This is Controller Page
                                .accessDeniedPage("/AccessDeniedPage"));

        return httpSecurity.build();
    }
}
