package com.api.mappers;

import com.api.dtos.CommentDto;
import com.api.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICommentMapper {
    ICommentMapper INSTANCE = Mappers.getMapper(ICommentMapper.class);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "threadId", source = "thread.id")
    CommentDto commentToCommentDto(Comment comment);

    List<CommentDto> commentsToCommentsDto(List<Comment> comments);
}
