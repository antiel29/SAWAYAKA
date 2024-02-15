package com.api.interfaces;

import com.api.dtos.CommentDto;
import com.api.dtos.CommentNewDto;
import com.api.models.CommentEntity;
import com.api.models.ThreadEntity;
import com.api.models.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ICommentService {

    List<CommentDto> getComments();
    List<CommentDto> getCurrentComments();

    List<CommentDto> getThreadComments(Long threadId);
    CommentDto newComment(CommentNewDto provided);

    CommentEntity createComment(String content, LocalDateTime creationDateTime, UserEntity user, ThreadEntity thread);

    boolean deleteComment(Long id);


    CommentDto getComment(Long id);


}
