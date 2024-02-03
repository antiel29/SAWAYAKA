package com.api.repository;

import com.api.models.Comment;
import com.api.models.ThreadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByThreadId(Long threadId);
    List<Comment> findByUserId(Long userId);
}
