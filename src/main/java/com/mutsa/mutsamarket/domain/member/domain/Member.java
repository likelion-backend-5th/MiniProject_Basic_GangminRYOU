package com.mutsa.mutsamarket.domain.member.domain;

import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	private String phoneNumber;
	private String address;

	@Builder
	public Member(String email, String password, String phoneNumber, String address) {
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public boolean validateEmail(){
		final String validateRegex = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$'";
		return !(Pattern.matches(validateRegex, this.email));
	}

	public void encode(PasswordEncoder passwordEncoder){
		this.password = passwordEncoder.encode(password);
	}

}
