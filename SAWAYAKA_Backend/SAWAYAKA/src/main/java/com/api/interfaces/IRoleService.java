package com.api.interfaces;

import com.api.models.Role;
import com.api.models.UserEntity;

public interface IRoleService {
    Role getAdminRole();

    Role getUserRole();

     void assignUserRoles(UserEntity user, Role... roles);

    void disAssignUserRoles(UserEntity user);
}
