package com.api.services;

import com.api.dtos.CommentDto;
import com.api.dtos.CommentNewDto;
import com.api.interfaces.ICommentService;
import com.api.interfaces.IThreadService;
import com.api.interfaces.IUserService;
import com.api.mappers.ICommentMapper;
import com.api.models.CommentEntity;
import com.api.models.ThreadEntity;
import com.api.models.UserEntity;
import com.api.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class CommentService implements ICommentService {

    private final ICommentRepository commentRepository;

    private final IThreadService threadService;
    private final IUserService userService;
    private final ICommentMapper commentMapper;

    @Autowired
    public CommentService(ICommentRepository commentRepository, IThreadService threadService, IUserService userService, ICommentMapper commentMapper) {

        this.commentRepository = commentRepository;
        this.threadService = threadService;
        this.userService = userService;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDto> getComments() {
        List<CommentEntity> comments = commentRepository.findAll();
        return commentMapper.commentsToCommentsDto(comments);
    }

    @Override
    public CommentDto getComment(Long id) {
        CommentEntity comment = commentRepository.findById(id).orElse(null);
        return commentMapper.commentToCommentDto(comment);

    }

    @Override
    public List<CommentDto> getCurrentComments() {
        Long userId = userService.actualUser().getId();

        List<CommentEntity> comments = commentRepository.findByUserId(userId);
        return commentMapper.commentsToCommentsDto(comments);
    }

    @Override
    public List<CommentDto> getThreadComments(Long threadId) {
        List<CommentEntity> comments = commentRepository.findByThreadId(threadId);
        return commentMapper.commentsToCommentsDto(comments);
    }

    @Override
    public CommentDto newComment(CommentNewDto provided) {
        UserEntity user = userService.actualUser();
        ThreadEntity thread = threadService.getThread(provided.getThreadId());

        thread.setCommentCount(thread.getCommentCount()+1);
        thread.setLastActivity(LocalDateTime.now());
        threadService.saveThread(thread);

        CommentEntity newComment = new CommentEntity(provided.getContent(), LocalDateTime.now(), user, thread);
        commentRepository.save(newComment);

        return commentMapper.commentToCommentDto(newComment);
    }

    @Override
    public CommentEntity createComment(String content, LocalDateTime creationDateTime, UserEntity user, ThreadEntity thread) {

        thread.setCommentCount(thread.getCommentCount()+1);
        thread.setLastActivity(creationDateTime);
        threadService.saveThread(thread);

        CommentEntity newComment = new CommentEntity(content,creationDateTime, user, thread);

        commentRepository.save(newComment);
        return newComment;
    }

    @Override
    public boolean deleteComment(Long id) {
        Long userId = userService.actualUser().getId();

        CommentEntity comment = commentRepository.findById(id).orElse(null);

        if (Objects.equals(userId, comment.getUser().getId())) {
            commentRepository.delete(comment);
            return true;
        } else {
            return false;
        }
    }
}
