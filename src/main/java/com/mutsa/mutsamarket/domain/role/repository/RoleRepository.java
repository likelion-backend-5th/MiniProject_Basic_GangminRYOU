package com.mutsa.mutsamarket.domain.role.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutsa.mutsamarket.domain.role.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findAllByRoleName(String roleName);
}
