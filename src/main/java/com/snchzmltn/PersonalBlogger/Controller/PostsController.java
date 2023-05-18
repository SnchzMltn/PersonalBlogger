package com.snchzmltn.PersonalBlogger.Controller;

import com.snchzmltn.PersonalBlogger.Entities.Posts;
import com.snchzmltn.PersonalBlogger.Entities.Status;
import com.snchzmltn.PersonalBlogger.Services.PostsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class PostsController {

    @Autowired
    PostsServiceImpl postsService;

    @GetMapping("/posts")
    public ResponseEntity<List<Posts>> getAllPosts() {
        try {
            List<Posts> output = this.postsService.getAllPosts();

            if (output.size() > 0) return new ResponseEntity<>(output, HttpStatus.OK);;

            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.toString());

            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<Posts> createPost(
            @RequestParam String createdBy,
            @RequestParam Integer readTime,
            @RequestParam String status, //TODO: figure out how does this work
            @RequestParam String title,
            @RequestParam String htmlContent,
            @RequestParam String textContent
    ) {
        try {
            Integer currRecordCount = this.postsService.getCount();

            final Posts createdPost =
                    new Posts(currRecordCount,
                            OffsetDateTime.now(),
                            decodeStatusInput(status),
                            createdBy,
                            readTime,
                            title,
                            htmlContent,
                            textContent,
                            createdBy,
                            OffsetDateTime.now());

            return new ResponseEntity<>(createdPost, HttpStatus.OK);
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
