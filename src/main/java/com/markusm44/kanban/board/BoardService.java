package com.markusm44.kanban.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.markusm44.kanban.board.dto.CreateBoardRequest;
import com.markusm44.kanban.board.dto.UpdateBoardRequest;

import com.markusm44.kanban.boardlist.BoardListRepository;
import com.markusm44.kanban.common.api.SuccessResponseMessage;
import com.markusm44.kanban.task.TaskRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BoardService {
  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private BoardListRepository boardListRepository;

  @Autowired
  private TaskRepository taskRepository;

  public List<Board> getBoards() {
    List<Board> boards = boardRepository.findAll();
    return boards;
  }

  public SuccessResponseMessage createNewBoard(CreateBoardRequest boardData) {
    Board newBoard = new Board();

    newBoard.setName(boardData.getName());
    newBoard.setDescription(boardData.getDescription() != null ? boardData.getDescription() : "");

    Board savedBoard = boardRepository.save(newBoard);

    String responseMessage = "New board created.";
    SuccessResponseMessage response = new SuccessResponseMessage(HttpStatus.CREATED.value(), responseMessage,
        savedBoard.getId());

    return response;
  }

  public SuccessResponseMessage updateBoard(long id, UpdateBoardRequest boardData) {
    Board board = boardRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Board with id " + id + " not found."));

    if (boardData.getName() != null) {
      board.setName(boardData.getName());
    }
    if (boardData.getDescription() != null) {
      board.setDescription(boardData.getDescription());
    }

    boardRepository.save(board);

    String responseMessage = "Board updated successfully.";
    SuccessResponseMessage response = new SuccessResponseMessage(HttpStatus.OK.value(), responseMessage, board.getId());

    return response;
  }

  public SuccessResponseMessage deleteBoard(long id) {
    taskRepository.deleteAllByBoardId(id);
    boardListRepository.deleteAllByBoardId(id);
    boardRepository.deleteById(id);

    String responseMessage = "Board deleted successfully.";
    SuccessResponseMessage response = new SuccessResponseMessage(HttpStatus.OK.value(), responseMessage, id);

    return response;
  }
}
