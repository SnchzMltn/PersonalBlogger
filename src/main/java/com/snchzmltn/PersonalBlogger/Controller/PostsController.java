package com.snchzmltn.PersonalBlogger.Controller;

import com.snchzmltn.PersonalBlogger.Entities.Posts;
import com.snchzmltn.PersonalBlogger.Entities.Status;
import com.snchzmltn.PersonalBlogger.Services.PostsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
public class PostsController {

    @Autowired
    PostsServiceImpl postsService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/posts")
    public ResponseEntity<List<Posts>> getAllPosts() {
        try {
            List<Posts> output = this.postsService.getAllPosts();

            if (!output.isEmpty()) return new ResponseEntity<>(output, HttpStatus.OK);;

            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.toString());

            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Posts> getPostById(@PathVariable String id) {
        try {
            final var post = this.postsService.getPostById(id);

            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.toString());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<Posts> createPost(
            @RequestParam String createdBy,
            @RequestParam Integer readTime,
            @RequestParam String status,
            @RequestParam String title,
            @RequestParam String htmlContent,
            @RequestParam String markdownContent,
            @RequestParam String textContent
    ) {
        final Status decodedStatusInput = decodeStatusInput(status);

        try {

            final var newPost =
                    new Posts(UUID.randomUUID().toString(),
                            OffsetDateTime.now(),
                            decodedStatusInput,
                            createdBy,
                            readTime,
                            title,
                            htmlContent,
                            textContent,
                            markdownContent,
                            createdBy,
                            OffsetDateTime.now());

            this.postsService.createPost(newPost);

            return new ResponseEntity<>(newPost, HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Status decodeStatusInput(final String inputValue) {
        final Status.StatusEnum statusEnumValue = Status.StatusEnum.valueOf(inputValue.toUpperCase());
        return switch (statusEnumValue) {
            case DRAFT -> new Status(1, Status.StatusEnum.DRAFT);
            case PUBLISHED -> new Status(2, Status.StatusEnum.PUBLISHED);
        };
    }
}
