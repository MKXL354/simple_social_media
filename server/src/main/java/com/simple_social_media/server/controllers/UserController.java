package com.simple_social_media.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simple_social_media.server.models.Post;
import com.simple_social_media.server.models.User;
import com.simple_social_media.server.repositories.PostRepo;
import com.simple_social_media.server.repositories.UserRepo;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PostRepo postRepo;

    @PostMapping("/{id}/post")
    public String post(@PathVariable("id") int id, @RequestBody Post post) {
        postRepo.save(post);
        return "success";
    }

    @GetMapping("/{id}/posts")
    public List<Post> getPost(@PathVariable("id") int id) {
        User user = userRepo.findById(id).orElse(null);
        return postRepo.findByUser(user);
    }

    @DeleteMapping("/{userId}/deletePost/{postId}")
    public String deletePost(@PathVariable("postId") int postId) {
        Post post = postRepo.findById(postId).orElse(null);
        try {
            postRepo.delete(post);
            return "success";
        } catch (InvalidDataAccessApiUsageException e) {
            return "no post found";
        }
    }

    @PutMapping("/{userId}/likePost/{postId}")
    public Post likePost(@PathVariable("userId") int userId, @PathVariable("postId") int postId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Post post = postRepo.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found"));
        try {
            if(!user.getLikedPosts().contains(post)){
                user.getLikedPosts().add(post);
                post.getUsersLiked().add(user);
                userRepo.save(user);
                postRepo.save(post);
            }
        } catch (EntityNotFoundException e) {
            return null;
        }
        return post;
    }
}
// like, delete like, follow, delete follow, main page