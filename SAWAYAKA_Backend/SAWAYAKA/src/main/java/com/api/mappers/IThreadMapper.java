package com.api.mappers;

import com.api.models.ThreadEntity;
import com.api.dtos.ThreadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IThreadMapper {
    IThreadMapper INSTANCE = Mappers.getMapper(IThreadMapper.class);

    @Mapping(target = "userId",source = "user.id")
    ThreadDto threadToThreadDto(ThreadEntity thread);

    List<ThreadDto> threadsToThreadsDto(List<ThreadEntity> threads);


}
