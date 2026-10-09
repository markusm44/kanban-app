package com.markusm44.kanban.boardlist;

import jakarta.persistence.*;

@Entity
@Table(name = "board_lists")
public class BoardList {
    private long id;
    private long boardId;
    private String name;

    public BoardList() {
    }

    public BoardList(long boardId, String name) {
        this.boardId = boardId;
        this.name = name;
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

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}