package com.api.mappers;

import com.api.dtos.CommentDto;
import com.api.models.CommentEntity;
import com.api.models.ThreadEntity;
import com.api.models.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-17T22:07:48-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240206-1609, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class ICommentMapperImpl implements ICommentMapper {

    @Override
    public CommentDto commentToCommentDto(CommentEntity comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setUserId( commentUserId( comment ) );
        commentDto.setThreadId( commentThreadId( comment ) );
        commentDto.setId( comment.getId() );
        commentDto.setContent( comment.getContent() );
        commentDto.setCreationDateTime( comment.getCreationDateTime() );

        return commentDto;
    }

    @Override
    public List<CommentDto> commentsToCommentsDto(List<CommentEntity> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentDto> list = new ArrayList<CommentDto>( comments.size() );
        for ( CommentEntity commentEntity : comments ) {
            list.add( commentToCommentDto( commentEntity ) );
        }

        return list;
    }

    private Long commentUserId(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            return null;
        }
        UserEntity user = commentEntity.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long commentThreadId(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            return null;
        }
        ThreadEntity thread = commentEntity.getThread();
        if ( thread == null ) {
            return null;
        }
        Long id = thread.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
