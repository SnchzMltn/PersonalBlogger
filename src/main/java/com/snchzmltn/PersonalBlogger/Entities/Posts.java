package com.snchzmltn.PersonalBlogger.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    OffsetDateTime createdTime;

    @ManyToOne(cascade = CascadeType.ALL)
    Status status;

    String createdBy;

    Integer readTime;

    String title;

    String htmlContent;

    String textContent;

    String updatedBy;

    OffsetDateTime updatedTime;
}
