package com.taskcodee.server.DTOs;

public class BoardMemberDTO {

    private Long userId;

    private Long boardId;

    private String role;

    public BoardMemberDTO() {
    }

    public BoardMemberDTO(Long userId, Long boardId, String role) {
        this.userId = userId;
        this.boardId = boardId;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
