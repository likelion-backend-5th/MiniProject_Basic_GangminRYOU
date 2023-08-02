package com.mutsa.mutsamarket.domain.role.entity;

import java.util.ArrayList;
import java.util.List;

import com.mutsa.mutsamarket.domain.member.entity.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    private List<Member> members;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_authorities")
    private List<Authority> authorities = new ArrayList<>();

    @Builder
    public Role(String roleName, List<Member> members, List<Authority> authorities) {
        this.roleName = roleName;
        this.members = members;
        this.authorities = authorities;
    }
}
