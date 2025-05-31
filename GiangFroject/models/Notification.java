package com.example.GiangFroject.models;

import jakarta.persistence.*;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String message;
    private boolean pending = true;

    public Notification() {}

    public Notification(Long userId, String message) {
        this.userId = userId;
        this.message = message;
        this.pending = true;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public boolean isPending() {
        return pending;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }
}