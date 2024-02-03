package com.api.services;

import com.api.interfaces.IRoleService;
import com.api.models.Role;
import com.api.models.UserEntity;
import com.api.repository.IRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleService implements IRoleService {

    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getAdminRole() {
        return roleRepository.findByName("ADMIN").orElse(null);
    }

    public Role getUserRole() {
        return roleRepository.findByName("USER").orElse(null);
    }

    public void assignUserRoles(UserEntity user, Role... roles) {
        user.getRoles().addAll(Arrays.asList(roles));
    }

    public void disAssignUserRoles(UserEntity user) {user.getRoles().clear();}
}
