package com.skillstorm.taxprep.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true) // what allows for security checks
public class SecurityConfiguration {
  
  /**
   * some deprecated methods to note when researching
   * 
   * SecurityConfiguration class used to extend WebSecurityConfigureAdapter (deprecated)
   * 
   * authorizeRequests() deprecated in favor of authorizeHttpRequests
   * 
   * mvcMatchers() & antMatchers() deprecated for requestMatchers
   *    - requestMatchers offers different flavors to faciltate various ways of restricting requests that were supported by the deprecated methods
   * 
   * 
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    
    // using HttpSecurity to configure which endpoints require authentication
    http.authorizeHttpRequests(authorizeHttpRequests ->
      // allows access to /users/hello endpoint w/o need for authentication
      authorizeHttpRequests
        .requestMatchers("/").permitAll()
    )
    .formLogin(Customizer.withDefaults())
    .httpBasic(withDefaults());
    
    return http.build();
  }

  /**
   * BCrypt uses hasing so there is a predictable result each time
   * 
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    // going to hash passwords for us
    // by changing each character to something else
    // we can specify how many iterations the encoder runs the hashing algorithm
    // the strength parameter specifies that number
    // the # of iterations run will be 2^strength times
    return new BCryptPasswordEncoder(4);
  }

}

