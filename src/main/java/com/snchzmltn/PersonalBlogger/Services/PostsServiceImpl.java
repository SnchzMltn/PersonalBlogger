package com.snchzmltn.PersonalBlogger.Services;

import com.snchzmltn.PersonalBlogger.Entities.Posts;
import com.snchzmltn.PersonalBlogger.Repository.PostsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostsServiceImpl implements PostsServiceI {

    @Autowired(required = false)
    PostsRepository postsRepository;

    @Override
    public List<Posts> getAllPosts() {
        return this.postsRepository.findAll();
    }

    @Override
    public Posts getPostById(String id) {
        return this.postsRepository.findById(id).orElseThrow();
    }

    @Override
    public void createPost(final Posts post) {
        Posts saved = this.postsRepository.saveAndFlush(post);
        log.debug("Created POST entity with ID: " + saved.getId());
    }

    public Integer getCount() {
        return Long.valueOf(this.postsRepository.count()).intValue();
    }
}
