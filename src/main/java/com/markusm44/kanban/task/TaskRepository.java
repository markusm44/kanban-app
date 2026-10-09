package com.markusm44.kanban.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByBoardId(long boardId);

  @Modifying
  @Transactional
  @Query("DELETE FROM Task t WHERE t.listId = :listId")
  int deleteAllByListId(long listId);

  @Modifying
  @Transactional
  @Query("DELETE FROM Task t WHERE t.boardId = :boardId")
  int deleteAllByBoardId(long boardId);
}
