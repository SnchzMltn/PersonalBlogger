package com.snchzmltn.PersonalBlogger.Services;

import com.snchzmltn.PersonalBlogger.Entities.Posts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostsServiceI {
    List<Posts> getAllPosts();

    Posts getPostById(String id);

    void createPost(final Posts posts);
}
