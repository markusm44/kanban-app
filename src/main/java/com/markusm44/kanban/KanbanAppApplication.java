package com.markusm44.kanban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.markusm44.kanban.board", "com.markusm44.kanban.boardlist",
		"com.markusm44.kanban.task" })
public class KanbanAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbanAppApplication.class, args);
	}
}
