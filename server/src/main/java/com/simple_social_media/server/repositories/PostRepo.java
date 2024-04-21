package com.simple_social_media.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.simple_social_media.server.models.Post;

@RepositoryRestResource(collectionResourceRel = "posts", path = "posts")
public interface PostRepo extends JpaRepository<Post, Integer>{
    
}
