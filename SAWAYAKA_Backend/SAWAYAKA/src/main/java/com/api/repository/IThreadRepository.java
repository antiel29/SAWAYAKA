package com.api.repository;

import com.api.models.ThreadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IThreadRepository extends JpaRepository<ThreadEntity,Long> {
    List<ThreadEntity> findByUserId(Long userId);
}
