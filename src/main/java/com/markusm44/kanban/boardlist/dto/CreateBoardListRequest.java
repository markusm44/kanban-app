package com.markusm44.kanban.boardlist.dto;

public class CreateBoardListRequest {
  private long boardId;
  private String name;

  public long getBoardId() {
    return boardId;
  }

  public void setBoardId(long boardId) {
    this.boardId = boardId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
