package com.mutsa.mutsamarket.config;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mutsa.mutsamarket.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtUtils jwtUtils;
	private final UserDetailsManager userDetailsManager;
	private static final String BEARER_PREFIX = "Bearer ";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(authorization != null){
			String accessToken = resolveToken(authorization);
			SecurityContext context = SecurityContextHolder.createEmptyContext();
			Claims claims = jwtUtils.parseClaims(accessToken);
			String email = claims.getSubject();
			UserDetails userDetails = userDetailsManager.loadUserByUsername(email);
			AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, accessToken, userDetails.getAuthorities());
			context.setAuthentication(authenticationToken);
			SecurityContextHolder.setContext(context);
		}else {
			log.info("jwt validation failed");
		}
		filterChain.doFilter(request, response);
	}


	private String resolveToken(String authorization){
		if(authorization.startsWith(BEARER_PREFIX)){
			String accessToken = authorization.substring(BEARER_PREFIX.length());
			jwtUtils.validate(accessToken);
			return accessToken;
		}
		return null;
	}
}
