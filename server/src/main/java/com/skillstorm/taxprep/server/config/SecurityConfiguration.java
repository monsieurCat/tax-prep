package com.skillstorm.taxprep.server.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.skillstorm.taxprep.server.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;

@Configuration
//@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true) // what allows for security checks
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityConfiguration {

  @Autowired
  UserService userService;

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfig = new CorsConfiguration();

    corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
    corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
    corsConfig.setAllowCredentials(true);
    corsConfig.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);

    return source;
  }

  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration corsConfig = new CorsConfiguration();

    corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
    corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
    corsConfig.setAllowCredentials(true);
    corsConfig.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);

      return new CorsFilter(source);
   }
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(authorizeHttpRequests -> {
      authorizeHttpRequests
        .requestMatchers("/").permitAll()
        .requestMatchers("/api/auth/**").permitAll()
        .requestMatchers("/login").permitAll()
        .requestMatchers("/user/**").hasRole("USER")
        .requestMatchers("/address/**").hasRole("USER")
        .requestMatchers("/tax_info/**").hasRole("USER")
        .anyRequest().authenticated();
    })
    .addFilterBefore(corsFilter(), SessionManagementFilter.class)
    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
    .sessionManagement(sm -> sm.sessionFixation().changeSessionId())
    .csrf(csrf -> csrf.disable())
    .formLogin(form -> form
      .defaultSuccessUrl("/user/info")
      .permitAll())
    .logout(logout -> logout
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