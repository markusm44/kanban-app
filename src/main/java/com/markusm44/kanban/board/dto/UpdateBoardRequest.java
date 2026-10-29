package com.markusm44.kanban.board.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateBoardRequest {
  @Pattern(regexp = "(?s).*\\S.*", message = "must not be blank")
  @Size(max = 255)
  private String name;

  @Size(max = 255)
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
