package com.markusm44.kanban.task;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    private long id;
    private long boardId;
    private long listId;
    private String name;
    private String description;
    private Boolean completed;
    private String tags;

    public Task() {
    }

    public Task(long boardId, long listId, String name, String description, Boolean completed,
            String tags) {
        this.boardId = boardId;
        this.listId = listId;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.tags = tags;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "board_id", nullable = false)
    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    @Column(name = "list_id", nullable = false)
    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", columnDefinition = "VARCHAR(255) DEFAULT ''", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "completed", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Column(name = "tags", columnDefinition = "VARCHAR(255) DEFAULT ''", nullable = false)
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}