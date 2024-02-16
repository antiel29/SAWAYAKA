package com.api.interfaces;

import com.api.dtos.ThreadDto;
import com.api.dtos.ThreadNewDto;
import com.api.models.ThreadEntity;
import com.api.models.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IThreadService {

    List<ThreadDto> getThreads();

    ThreadDto getThreadDto(Long id);

    ThreadEntity getThread(Long id);

    List<ThreadDto> getCurrentThreads();

    ThreadDto newThread(ThreadNewDto provided);
    void saveThread(ThreadEntity thread);
    ThreadEntity createThread(String title, String content, LocalDateTime creationDateTime, UserEntity user);
    boolean deleteThread(Long id);
}
