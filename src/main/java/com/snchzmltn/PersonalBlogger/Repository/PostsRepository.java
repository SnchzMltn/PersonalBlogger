package com.snchzmltn.PersonalBlogger.Repository;

import com.snchzmltn.PersonalBlogger.Entities.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, String> {
}
