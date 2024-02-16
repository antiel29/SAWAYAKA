package com.api.dtos;

import jakarta.validation.constraints.Size;

public class ThreadNewDto {
    @Size(min=1,max=1000)
    private String title;

    @Size(min=1,max=10000)
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
