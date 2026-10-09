package com.markusm44.kanban.board;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.markusm44.kanban.board.dto.CreateBoardRequest;
import com.markusm44.kanban.board.dto.UpdateBoardRequest;
import com.markusm44.kanban.common.api.ErrorResponseMessage;
import com.markusm44.kanban.common.api.SuccessResponseMessage;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class BoardController {
  @Autowired
  private BoardRepository boardRepository;

  private final BoardService boardService;

  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping("/boards")
  public ResponseEntity<List<Board>> getBoards() {
    List<Board> boards = boardService.getBoards();
    return ResponseEntity.ok(boards);
  }

  @PostMapping("/board")
  public ResponseEntity<?> createBoard(@Valid @RequestBody CreateBoardRequest boardRequest) {
    if (boardRequest.getName() == null) {
      String responseMessage = "Missing required field 'name'.";
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponseMessage(HttpStatus.BAD_REQUEST.value(),
              responseMessage));
    }

    SuccessResponseMessage response = boardService.createNewBoard(boardRequest);
    return ResponseEntity.created(null).body(response);
  }

  @PutMapping("/board/{id}")
  public ResponseEntity<?> updateBoard(@PathVariable long id, @RequestBody UpdateBoardRequest boardDetails) {
    if (boardRepository.findById(id).isEmpty()) {
      String responseMessage = "Board with id " + id + " not found.";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), responseMessage));
    }

    SuccessResponseMessage response = boardService.updateBoard(id, boardDetails);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/board/{id}")
  public ResponseEntity<?> deleteBoard(@PathVariable long id) {
    if (boardRepository.findById(id).isEmpty()) {
      String responseMessage = "Board with id " + id + " not found.";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), responseMessage));
    }

    SuccessResponseMessage response = boardService.deleteBoard(id);
    return ResponseEntity.ok(response);
  }
}