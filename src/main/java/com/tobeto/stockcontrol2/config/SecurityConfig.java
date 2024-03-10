package com.tobeto.stockcontrol2.config;

import com.tobeto.stockcontrol2.filter.JwtAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

	private JwtAuthorizationFilter jwtAuthorizationFilter;


	private static final String[] WHITE_LIST_URLS = {
			"/swagger-ui/**",
			"/v2/api-docs",
			"/v3/api-docs",
			"/v3/api-docs/**"

	};

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		http
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(WHITE_LIST_URLS).permitAll()
						.requestMatchers("/api/v1/login", "/api/v1/signup").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/products/**").hasAnyRole("ROLE_ADMIN")
				.anyRequest().authenticated()
				)
		     .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		     .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
		// @formatter:on

		return http.build();
	}

	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
