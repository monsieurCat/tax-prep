package com.skillstorm.taxprep.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.skillstorm.taxprep.server.models.AuthenticationSuccessHandler;
import com.skillstorm.taxprep.server.services.UserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true) // what allows for security checks
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityConfiguration {

  @Autowired
  UserService userService;
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(authorizeHttpRequests -> {
      authorizeHttpRequests
        .requestMatchers("/api/auth/login").permitAll()
        .requestMatchers("/privateData").authenticated()
        .anyRequest().authenticated();
    })
    .csrf(csrf -> csrf.disable())
    //.formLogin(Customizer.withDefaults())
    //.httpBasic(withDefaults())
    .formLogin(form -> form
      .loginPage("/api/auth/login")
      .loginProcessingUrl("/login")
      .failureUrl("/api/auth/login?error")
      .defaultSuccessUrl("/user/home")
      .permitAll())
    //.oauth2Login(withDefaults())
    .logout(logout -> logout
      .invalidateHttpSession(true)
      .clearAuthentication(true)
      .logoutRequestMatcher(new AntPathRequestMatcher("/api/auth/logout"))
      .logoutSuccessUrl("/api/auth/login?logout"))
    .cors(cors -> {
      cors.configurationSource(request -> {
        CorsConfiguration cors_config = new CorsConfiguration();

        cors_config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        cors_config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        cors_config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        cors_config.setAllowCredentials(true);
        cors_config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", cors_config);

        return cors_config;
      });
    });
    
    return http.build();
  }

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(4);
  }
}

