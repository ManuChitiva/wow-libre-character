package com.wow.libre.infrastructure.security;

import com.wow.libre.infrastructure.filter.JwtTokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

  private final JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;

  public SecurityConfiguration(JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter) {
    this.jwtTokenAuthenticationFilter = jwtTokenAuthenticationFilter;
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfigurationSource = new CorsConfiguration();
    corsConfigurationSource.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://127.0.0.1:3000"));
    corsConfigurationSource.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.DELETE.name()));
    corsConfigurationSource.setAllowedHeaders(List.of(HttpHeaders.CONTENT_TYPE,
            HttpHeaders.AUTHORIZATION));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfigurationSource);
    return source;
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .cors(withDefaults())
            .csrf(csrf -> csrf
                    .ignoringRequestMatchers("/api/resources/country",
                            "/api/auth/login",
                            "/api/account",
                            "/swagger-ui-custom/**",
                            "/v2/api-docs",
                            "/configuration/ui",
                            "/swagger-resources/**",
                            "/configuration/security",
                            "/swagger-ui.html",
                            "/webjars/**",
                            "/webjars/swagger-ui/**"))
            .authorizeHttpRequests(request ->
                    request.requestMatchers("/**")
                            .permitAll()
                            .anyRequest().authenticated())
            .sessionManagement(manager ->
                    manager.sessionCreationPolicy(STATELESS))
            .addFilterBefore(jwtTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
          throws Exception {
    return config.getAuthenticationManager();
  }


}
