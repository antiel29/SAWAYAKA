package com.api.mappers;

import com.api.dtos.ThreadDto;
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
public class IThreadMapperImpl implements IThreadMapper {

    @Override
    public ThreadDto threadToThreadDto(ThreadEntity thread) {
        if ( thread == null ) {
            return null;
        }

        ThreadDto threadDto = new ThreadDto();

        threadDto.setUserId( threadUserId( thread ) );
        threadDto.setLastActivity( thread.getLastActivity() );
        threadDto.setCommentCount( thread.getCommentCount() );
        threadDto.setId( thread.getId() );
        threadDto.setTitle( thread.getTitle() );
        threadDto.setContent( thread.getContent() );
        threadDto.setCreationDateTime( thread.getCreationDateTime() );

        return threadDto;
    }

    @Override
    public List<ThreadDto> threadsToThreadsDto(List<ThreadEntity> threads) {
        if ( threads == null ) {
            return null;
        }

        List<ThreadDto> list = new ArrayList<ThreadDto>( threads.size() );
        for ( ThreadEntity threadEntity : threads ) {
            list.add( threadToThreadDto( threadEntity ) );
        }

        return list;
    }

    private Long threadUserId(ThreadEntity threadEntity) {
        if ( threadEntity == null ) {
            return null;
        }
        UserEntity user = threadEntity.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
