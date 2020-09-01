package com.bikki.bankingapplication.service;

import com.bikki.bankingapplication.domain.entity.Role;


public interface RoleService {

    Role findByRoleName(String roleName);
}
