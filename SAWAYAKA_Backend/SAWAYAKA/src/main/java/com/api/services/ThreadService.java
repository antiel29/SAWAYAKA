package com.api.services;

import com.api.dtos.ThreadDto;
import com.api.dtos.ThreadNewDto;
import com.api.interfaces.IThreadService;
import com.api.interfaces.IUserService;
import com.api.mappers.IThreadMapper;
import com.api.models.ThreadEntity;
import com.api.models.UserEntity;
import com.api.repository.IThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ThreadService implements IThreadService {

    private final IUserService userService;
    private  final IThreadRepository threadRepository;
    private final IThreadMapper threadMapper;

    @Autowired
    ThreadService(IUserService userService, IThreadRepository threadRepository, IThreadMapper threadMapper){
        this.userService = userService;
        this.threadRepository = threadRepository;
        this.threadMapper = threadMapper;
    }

    @Override
    public List<ThreadDto> getThreads() {
        List<ThreadEntity> threads = threadRepository.findAll();
        return threadMapper.threadsToThreadsDto(threads);
    }

    @Override
    public ThreadDto getThreadDto(Long id){
        ThreadEntity thread = threadRepository.findById(id).orElse(null);
        return threadMapper.threadToThreadDto(thread);
    }

    @Override
    public ThreadEntity getThread(Long id){
        return threadRepository.findById(id).orElse(null);
    }

    @Override
    public List<ThreadDto> getCurrentThreads() {
        Long userId = userService.actualUser().getId();
        List<ThreadEntity> threads = threadRepository.findByUserId(userId);
        return threadMapper.threadsToThreadsDto(threads);
    }

    @Override
    public void newThread(ThreadNewDto provided) {
        UserEntity user = userService.actualUser();

        ThreadEntity newThread = new ThreadEntity(provided.getTitle(),provided.getContent(), LocalDateTime.now(),user);

        threadRepository.save(newThread);
    }

    @Override
    public boolean deleteThread(Long id) {
        Long userId = userService.actualUser().getId();

        ThreadEntity thread = threadRepository.findById(id).orElse(null);

        if(Objects.equals(userId, thread.getUser().getId())){
            threadRepository.delete(thread);
            return true;
        }
        else{
            return false;
        }

    }


}
