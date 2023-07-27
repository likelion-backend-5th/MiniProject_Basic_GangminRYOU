package com.mutsa.mutsamarket.config;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mutsa.mutsamarket.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtUtils jwtUtils;
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

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
				accessToken, new ArrayList<>());
			context.setAuthentication(authenticationToken);
			SecurityContextHolder.setContext(context);
		}
		filterChain.doFilter(request, response);
	}


	private String resolveToken(String authorization){
		if(authorization.startsWith(BEARER_PREFIX)){
			int start = authorization.indexOf(BEARER_PREFIX);
			String accessToken = authorization.substring(start);
			jwtUtils.validate(accessToken);
			return accessToken;
		}
		return null;
	}
}
