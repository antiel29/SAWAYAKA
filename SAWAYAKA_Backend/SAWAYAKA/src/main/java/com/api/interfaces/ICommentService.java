package com.api.interfaces;

import com.api.dtos.CommentDto;
import com.api.dtos.CommentNewDto;
import com.api.models.Comment;

import java.util.List;

public interface ICommentService {

    List<CommentDto> getComments();
    List<CommentDto> getCurrentComments();

    List<CommentDto> getThreadComments(Long threadId);
    void newComment(CommentNewDto provided);


    boolean deleteComment(Long id);


    CommentDto getComment(Long id);


}
