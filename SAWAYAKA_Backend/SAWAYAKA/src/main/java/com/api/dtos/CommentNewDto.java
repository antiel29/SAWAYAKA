package com.api.dtos;

import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class CommentNewDto {

    private Long threadId;
    @Size(min=1,max=10000)
    private String content;

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
