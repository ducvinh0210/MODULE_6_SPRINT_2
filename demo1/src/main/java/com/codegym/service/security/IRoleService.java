package com.codegym.service.security;

import com.codegym.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAllRole();

    List<Role> getAllRoles();

    void saveCreateGmail(String email);

    List<Role> getRoleByUsername(String email);
}
