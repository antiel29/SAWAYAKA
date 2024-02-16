package com.api.repository;

import com.api.models.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<CommentEntity,Long> {

    List<CommentEntity> findByThreadId(Long threadId);
    List<CommentEntity> findByUserId(Long userId);
}
