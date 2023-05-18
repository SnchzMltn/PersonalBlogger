package com.snchzmltn.PersonalBlogger.Services;

import com.snchzmltn.PersonalBlogger.Entities.Status;
import com.snchzmltn.PersonalBlogger.Repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusServiceI {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> getAllStatus() {
        return this.statusRepository.findAll();
    }
}
