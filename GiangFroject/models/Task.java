package com.example.GiangFroject.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime targetDate;
    private boolean completed;
    private boolean deleted;

    public Task() {}

    public Task(Long userId, String title, String description, LocalDateTime targetDate) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.targetDate = targetDate;
        this.completed = false;
        this.deleted = false;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getTargetDate() {
        return targetDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setTargetDate(LocalDateTime targetDate) {
        this.targetDate = targetDate;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
