package com.codegym.service.security.impl;

import com.codegym.model.Role;
import com.codegym.repository.IRoleRepository;
import com.codegym.service.security.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.finAllRole();
    }

    @Override
    public void saveCreateGmail(String email) {
        roleRepository.insertRoleGmail(email);
    }

    @Override
    public List<Role> getRoleByUsername(String email) {
        return roleRepository.findRoleByUsername(email);
    }
}
