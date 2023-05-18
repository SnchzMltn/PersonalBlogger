package com.snchzmltn.PersonalBlogger.Controller;

import com.snchzmltn.PersonalBlogger.Entities.Status;
import com.snchzmltn.PersonalBlogger.Services.StatusServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class StatusController {

    @Autowired
    StatusServiceImpl statusService;

    @GetMapping("/status")
    public ResponseEntity<List<Status>> getAllStatus() {
        try {
            List<Status> output = this.statusService.getAllStatus();

            if (output.size() > 0) return new ResponseEntity<>(output, HttpStatus.OK);

            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.toString());

            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
