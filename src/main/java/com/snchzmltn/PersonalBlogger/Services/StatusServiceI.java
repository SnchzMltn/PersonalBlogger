package com.snchzmltn.PersonalBlogger.Services;

import com.snchzmltn.PersonalBlogger.Entities.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatusServiceI {
    List<Status> getAllStatus();
}
