package com.bikki.bankingapplication.service.impl;

import com.bikki.bankingapplication.repository.RoleRepository;
import com.bikki.bankingapplication.domain.entity.Role;
import com.bikki.bankingapplication.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
