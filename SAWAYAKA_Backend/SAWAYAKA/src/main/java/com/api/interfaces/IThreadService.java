package com.api.interfaces;

import com.api.dtos.ThreadDto;
import com.api.dtos.ThreadNewDto;
import com.api.models.ThreadEntity;

import java.util.List;

public interface IThreadService {

    List<ThreadDto> getThreads();

    ThreadDto getThreadDto(Long id);

    ThreadEntity getThread(Long id);

    List<ThreadDto> getCurrentThreads();

    void newThread(ThreadNewDto provided);

    boolean deleteThread(Long id);
}
