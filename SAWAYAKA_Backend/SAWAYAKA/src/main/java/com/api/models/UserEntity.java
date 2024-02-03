package com.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    @Size(min=3,max=60)
    private String username;

    @Column(nullable = false)
    @Size(min=5,max=60)
    private String password;

    @Column(nullable = false)
    @Size(min=3,max=60)
    private String name;
    private boolean approved;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<ThreadEntity> threads;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name ="user_roles", joinColumns =
            @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();


    public UserEntity(){}

    public UserEntity(String username, String password){
        this.username = username;
        this.password = password;
        this.name = "Anonymous Kita High Student";
        this.approved = true;
    }

    public UserEntity(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
        this.approved = true;

    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<ThreadEntity> getThreads() {
        return threads;
    }

    public void setThreads(List<ThreadEntity> threads) {
        this.threads = threads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
