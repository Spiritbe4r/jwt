package com.cardenas.rest.service.impl;

import com.cardenas.rest.entity.Role;
import com.cardenas.rest.repository.RoleRepository;
import com.cardenas.rest.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Role getByRoleName(String userRoles) {
        return roleRepository.findByRoleName(userRoles);
    }
}
