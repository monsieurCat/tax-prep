package com.skillstorm.taxprep.server.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true) // what allows for security checks
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    String BASE_URL = "https://group11.skillstorm-congo.com";
    String BASE_FRONT_URL = "https://taxstorm.skillstorm-congo.com";

    // permit users to register and authenticate, and require authentication to receive user details
    http
      .authorizeHttpRequests(authorizeHttpRequests -> {
      authorizeHttpRequests
        .requestMatchers("/api/auth/**").permitAll()
        .requestMatchers("/login").permitAll()
        .requestMatchers("/user/**").hasRole("USER")
        .requestMatchers("/address/**").hasRole("USER")
        .requestMatchers("/tax_info/**").hasRole("USER")
        .anyRequest().authenticated();
    })
    // configure cors
    .cors(cors -> cors.configurationSource(request -> {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("https://taxstorm.skillstorm-congo.com", 
                                                          "http://taxstorm.skillstorm-congo.com",
                                                          "http://localhost:3000"));
      configuration.setAllowedMethods(Arrays.asList("*"));
      configuration.setAllowedHeaders(Arrays.asList("*"));
      configuration.setAllowCredentials(true);
      return configuration;
  }))
    .csrf(csrf -> csrf.disable())
    .formLogin(form -> form
      .successHandler((req, res, auth) -> res.setStatus(HttpStatus.OK.value()))
      .failureHandler(new SimpleUrlAuthenticationFailureHandler())
      //.defaultSuccessUrl(BASE_URL + "/user/info", true)
      //.failureUrl(BASE_URL + "/")
      .permitAll())
    .logout(logout -> logout
      //.logoutUrl(BASE_URL + "/logout")
      //.logoutSuccessUrl(BASE_URL + "/")
      .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
      .invalidateHttpSession(true)
      .clearAuthentication(true)
      .deleteCookies("JSESSIONID"));
      
    
    return http.build();
  }

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(4);
  }
}