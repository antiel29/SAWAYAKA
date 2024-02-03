package com.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "threads")
public class ThreadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000,nullable = false)
    @Size(min=1,max=1000)
    private String title;

    @Column(length = 10000, nullable = false)
    @Size(min=1,max=10000)
    private String content;

    @Column(nullable = false)
    private LocalDateTime creationDateTime;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    @OneToMany(mappedBy = "thread",cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public ThreadEntity() {}

    public ThreadEntity(String title, String content, LocalDateTime creationDateTime, UserEntity user) {
        this.title = title;
        this.content = content;
        this.creationDateTime = creationDateTime;
        this.user = user;
    }

    public Long getId() {return id;}

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

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
