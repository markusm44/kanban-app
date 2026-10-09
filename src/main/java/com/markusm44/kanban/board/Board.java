package com.markusm44.kanban.board;

import jakarta.persistence.*;

@Entity
@Table(name = "boards")
public class Board {
    private long id;
    private String name;
    private String description;

    public Board() {
    }

    public Board(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}