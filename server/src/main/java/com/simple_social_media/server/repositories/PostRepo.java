package com.simple_social_media.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simple_social_media.server.models.Post;
import com.simple_social_media.server.models.User;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    public List<Post> findByUser(User user);

    @Query(nativeQuery = true, value = "select * from post where user_id = ?1")
    public List<Post> find(int userId);
}
