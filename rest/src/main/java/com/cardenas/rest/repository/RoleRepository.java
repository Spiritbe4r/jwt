package com.cardenas.rest.repository;

import com.cardenas.rest.entity.Role;
import com.cardenas.rest.enums.UserRoles;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    public Role findByRoleName(String roleName);


     //Optional<Role> findByUserRoles(UserRoles userRoles);
}
