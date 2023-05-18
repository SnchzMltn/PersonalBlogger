package com.snchzmltn.PersonalBlogger.Repository;

import com.snchzmltn.PersonalBlogger.Entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
}
