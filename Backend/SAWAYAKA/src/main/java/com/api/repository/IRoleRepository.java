package com.api.repository;
import com.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
