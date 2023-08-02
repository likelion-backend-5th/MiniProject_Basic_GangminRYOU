package com.mutsa.mutsamarket.domain.member.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.mutsa.mutsamarket.domain.membernegotiation.entity.MemberNegotiation;
import com.mutsa.mutsamarket.domain.role.entity.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
	@OneToMany(mappedBy = "member")
	private List<MemberNegotiation> memberNegotiations = new ArrayList<>();
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles")
	private List<Role> roles = new ArrayList<>();

	@Builder
	public Member(String email, String password, String phoneNumber, String address,
		List<MemberNegotiation> memberNegotiations, List<Role> roles) {
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.memberNegotiations = memberNegotiations;
		this.roles = roles;
	}
	public void setRoles(List<Role> roles){
		this.roles = roles;
	}

	public boolean validateEmail(){
		final String validateRegex = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$'";
		return !(Pattern.matches(validateRegex, this.email));
	}

	public void encode(PasswordEncoder passwordEncoder){
		this.password = passwordEncoder.encode(password);
	}
}
