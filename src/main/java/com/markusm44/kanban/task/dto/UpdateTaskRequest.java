package com.markusm44.kanban.task.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateTaskRequest {
  @Pattern(regexp = "(?s).*\\S.*", message = "must not be blank")
  @Size(max = 255)
  private String name;

  @Size(max = 255)
  private String description;
  private Boolean completed;

  @Size(max = 255)
  private String tags;

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

  public Boolean isCompleted() {
    return completed;
  }

  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }
}
