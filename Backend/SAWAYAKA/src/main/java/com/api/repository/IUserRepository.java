package com.api.repository;

import com.api.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
}
