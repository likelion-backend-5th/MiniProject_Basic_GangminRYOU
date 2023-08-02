package com.mutsa.mutsamarket.domain.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutsa.mutsamarket.domain.role.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
