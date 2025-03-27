package com.andy.waterdam.pojo;


public class MessageBoard {
    private Long id;
    private String message;
    private Long userId;

    public MessageBoard(Long id, String message, Long userId) {
        this.id = id;
        this.message = message;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MessageBoard{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", userId=" + userId +
                '}';
    }
}
