package com.example.GiangFroject.models;

public class Notification {
    private Long id;
    private Long userId;
    private String message;
    private boolean pending;

    public Notification() {}

    public Notification(Long id, Long userId, String message) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.pending = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isPending() { return pending; }
    public void setPending(boolean pending) { this.pending = pending; }
}