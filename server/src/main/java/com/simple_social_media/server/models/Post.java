package com.simple_social_media.server.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {
    
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private String text;

    @ManyToMany(mappedBy = "likedPosts")
    @JsonIgnore
    private List<User> usersLiked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "user= " + user.getUserName() + ", likes= " + usersLiked.size() + "\ntext=" + text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsersLiked() {
        return usersLiked;
    }

    public void setUsersLiked(List<User> usersLiked) {
        this.usersLiked = usersLiked;
    }

}
