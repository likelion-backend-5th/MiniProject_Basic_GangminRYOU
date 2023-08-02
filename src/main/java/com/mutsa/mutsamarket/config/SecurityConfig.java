package com.mutsa.mutsamarket.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtTokenFilter jwtTokenFilter;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(
			auth -> auth.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/authorization/role-user").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/auth/role-admin").hasRole("ADMIN")
				.requestMatchers("/auth/authority-read").hasAuthority("READ")
				.requestMatchers("/auth/authority-write").hasAuthority("WRITE")
				.requestMatchers(PathRequest.toH2Console()).permitAll()
				.requestMatchers("/error").permitAll()
				.anyRequest().authenticated()
		).headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
			.sessionManagement(
			session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		).addFilterBefore(jwtTokenFilter, AuthorizationFilter.class)
			.build();
	}




}
