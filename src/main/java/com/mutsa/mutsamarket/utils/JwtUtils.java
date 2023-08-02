package com.mutsa.mutsamarket.utils;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mutsa.mutsamarket.common.exception.AuthenticationException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private final Key jwtSigningKey;
	private final String JWT_SECRET;
	private final Long ACCESS_TOKEN_EXPIRATION;
	private final JwtParser jwtParser;


	public JwtUtils(
		@Value("${jwt.secret}") String JWT_SECRET,
		@Value("${jwt.access-token-expiration}")Long ACCESS_TOKEN_EXPIRATION) {
		this.JWT_SECRET = JWT_SECRET;
		this.ACCESS_TOKEN_EXPIRATION = ACCESS_TOKEN_EXPIRATION;
		jwtSigningKey = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
		jwtParser = Jwts.parserBuilder()
			.setSigningKey(jwtSigningKey)
			.build();
	}

	public String createAccessToken(UserDetails userDetails){
		return generateToken(userDetails, ACCESS_TOKEN_EXPIRATION);
	}

	public String generateToken(UserDetails userDetails, Long expirationTime){
		String authority = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
		Claims jwtClaims = Jwts.claims()
			.setSubject(userDetails.getUsername())
			.setIssuedAt(Date.from(Instant.now()))
			.setExpiration(Date.from(Instant.now().plusSeconds(expirationTime)));
		return Jwts.builder()
			.setClaims(jwtClaims)
			.claim("auth", authority)
			.signWith(jwtSigningKey)
			.compact();
	}

	public boolean validate(String token){
		try{
			jwtParser.parseClaimsJws(token);
			return true;
		}catch (JwtException ex){
			throw new AuthenticationException(ErrorCode.INVALID_TOKEN_ERROR);
		}
	}

	public Claims parseClaims(String token){
		return jwtParser
			.parseClaimsJws(token)
			.getBody();
	}
}
