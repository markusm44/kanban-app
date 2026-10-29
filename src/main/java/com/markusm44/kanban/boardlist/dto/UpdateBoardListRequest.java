package com.markusm44.kanban.boardlist.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateBoardListRequest {
  @Pattern(regexp = "(?s).*\\S.*", message = "must not be blank")
  @Size(max = 255)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
