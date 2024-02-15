package com.api.services;

import com.api.interfaces.IRoleService;
import com.api.models.Role;
import com.api.models.UserEntity;
import com.api.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleService implements IRoleService {

    private final IRoleRepository roleRepository;

    @Autowired
    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public Role createRole(String name) {
        Role newRole = new Role(name);
        roleRepository.save(newRole);
        return newRole;
    }
    @Override
    public Role getAdminRole() {
        return roleRepository.findByName("ADMIN").orElse(null);
    }
    @Override
    public Role getUserRole() {
        return roleRepository.findByName("USER").orElse(null);
    }
    @Override
    public void assignUserRoles(UserEntity user, Role... roles) {
        user.getRoles().addAll(Arrays.asList(roles));
    }
    @Override
    public void disAssignUserRoles(UserEntity user) {user.getRoles().clear();}
}
