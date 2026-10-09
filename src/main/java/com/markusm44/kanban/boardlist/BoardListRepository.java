package com.markusm44.kanban.boardlist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface BoardListRepository extends JpaRepository<BoardList, Long> {
  List<BoardList> findByBoardId(long boardId);

  @Modifying
  @Transactional
  @Query("DELETE FROM BoardList b WHERE b.boardId = :boardId")
  int deleteAllByBoardId(long boardId);
}
