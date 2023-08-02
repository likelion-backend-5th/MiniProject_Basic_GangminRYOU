package com.mutsa.mutsamarket.api.auth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.mutsa.mutsamarket.config.CustomUserDetails;
import com.mutsa.mutsamarket.domain.member.entity.Member;
import com.mutsa.mutsamarket.domain.member.repository.MemberRepository;
import com.mutsa.mutsamarket.domain.role.entity.Authority;
import com.mutsa.mutsamarket.domain.role.entity.Role;
import com.mutsa.mutsamarket.domain.role.repository.AuthorityRepository;
import com.mutsa.mutsamarket.domain.role.repository.RoleRepository;

@Service

public class JpaUserDetailsManager implements UserDetailsManager {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	private final AuthorityRepository authorityRepository;

	public JpaUserDetailsManager(MemberRepository memberRepository, PasswordEncoder passwordEncoder,
		RoleRepository roleRepository, AuthorityRepository authorityRepository) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
		this.authorityRepository = authorityRepository;

		Authority readAuthority = Authority.builder()
			.name("READ")
			.build();
		authorityRepository.save(readAuthority);
		Authority writeAuthority = Authority.builder()
			.name("WRITE")
			.build();
		authorityRepository.save(writeAuthority);
		Role userRole = Role.builder()
			.roleName("USER")
			.authorities(List.of(readAuthority))
			.build();
		roleRepository.save(userRole);
		Role adminRole = Role.builder()
			.roleName("ADMIN")
			.authorities(List.of(readAuthority, writeAuthority))
			.build();
		roleRepository.save(adminRole);
		Member user = Member.builder()
			.email("gangmini94@naver.com")
			.password(passwordEncoder.encode("1234"))
			.roles(List.of(userRole))
			.build();
		memberRepository.save(user);
		Member admin = Member.builder()
			.email("dbrkdals1994@google.com")
			.password(passwordEncoder.encode("1234"))
			.roles(List.of(adminRole))
			.build();
		memberRepository.save(admin);
	}

	@Override
	public void createUser(UserDetails user) {

	}

	@Override
	public void updateUser(UserDetails user) {

	}

	@Override
	public void deleteUser(String username) {

	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {

	}

	@Override
	public boolean userExists(String username) {
		return memberRepository.existsByEmail(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var foundUser = memberRepository.findByEmailOrThrow(username);
		return CustomUserDetails.builder()
			.email(username)
			.password(foundUser.getPassword())
			.address(foundUser.getAddress())
			.phoneNumber(foundUser.getPhoneNumber())
			.authorities(foundUser.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(String.format("ROLE_%s", role.getRoleName())))
				.collect(Collectors.toList()))
			.build();
	}
}
