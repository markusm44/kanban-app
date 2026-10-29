package com.markusm44.kanban.boardlist;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.markusm44.kanban.board.BoardRepository;

import com.markusm44.kanban.boardlist.dto.CreateBoardListRequest;
import com.markusm44.kanban.boardlist.dto.UpdateBoardListRequest;
import com.markusm44.kanban.common.api.ErrorResponseMessage;
import com.markusm44.kanban.common.api.SuccessResponseMessage;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class BoardListController {
  @Autowired
  private BoardListRepository boardListRepository;

  @Autowired
  private BoardRepository boardRepository;

  private final BoardListService boardListService;

  public BoardListController(BoardListService boardListService) {
    this.boardListService = boardListService;
  }

  @GetMapping("/{board_id}/lists")
  public ResponseEntity<?> getBoardLists(@PathVariable long board_id) {
    if (boardRepository.findById(board_id).isEmpty()) {
      String responseMessage = "Board with id " + board_id + " not found";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), responseMessage));
    }

    List<BoardList> lists = boardListService.getLists(board_id);
    return ResponseEntity.ok(lists);
  }

  @PostMapping("/list")
  public ResponseEntity<?> createBoardList(@Valid @RequestBody CreateBoardListRequest boardListRequest) {
    if (boardRepository.findById(boardListRequest.getBoardId()).isEmpty()) {
      String responseMessage = "Board with id " + boardListRequest.getBoardId() + " not found";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), responseMessage));
    }

    SuccessResponseMessage response = boardListService.createNewBoardList(boardListRequest);
    return ResponseEntity.created(null).body(response);
  }

  @PutMapping("/list/{id}")
  public ResponseEntity<?> updateBoardList(@PathVariable long id,
      @Valid @RequestBody UpdateBoardListRequest boardListRequest) {
    if (boardListRepository.findById(id).isEmpty()) {
      String responseMessage = "List with id " + id + "not found.";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), responseMessage));
    }

    SuccessResponseMessage response = boardListService.updateBoardList(id, boardListRequest);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/list/{id}")
  public ResponseEntity<?> deleteBoardList(@PathVariable long id) {
    if (boardListRepository.findById(id).isEmpty()) {
      String responseMessage = "List with id " + id + "not found.";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), responseMessage));
    }

    SuccessResponseMessage response = boardListService.deleteBoardList(id);
    return ResponseEntity.ok(response);
  }
}