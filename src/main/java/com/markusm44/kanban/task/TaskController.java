package com.markusm44.kanban.task;

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
import com.markusm44.kanban.boardlist.BoardListRepository;
import com.markusm44.kanban.common.api.ErrorResponseMessage;
import com.markusm44.kanban.common.api.SuccessResponseMessage;
import com.markusm44.kanban.task.dto.CreateTaskRequest;
import com.markusm44.kanban.task.dto.UpdateTaskRequest;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class TaskController {
  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private BoardListRepository boardListRepository;

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/{board_id}/tasks")
  public ResponseEntity<?> getTasks(@PathVariable long board_id) {
    if (boardRepository.findById(board_id).isEmpty()) {
      String responseMessage = "Board with id " + board_id + " not found.";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), responseMessage));
    }

    List<Task> tasks = taskService.getTasks(board_id);
    return ResponseEntity.ok(tasks);
  }

  @PostMapping("/task")
  public ResponseEntity<?> createTask(@Valid @RequestBody CreateTaskRequest taskRequest) {
    if (boardRepository.findById(taskRequest.getBoardId()).isEmpty()) {
      String responseMessage = "Board with id " + taskRequest.getBoardId() + " not found.";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), responseMessage));
    }

    if (boardListRepository.findById(taskRequest.getListId()).isEmpty()) {
      String responseMessage = "List with id " + taskRequest.getListId() + " not found.";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), responseMessage));
    }

    if (boardListRepository.findByBoardId(taskRequest.getBoardId()).isEmpty()) {
      String responseMessage = "List " + taskRequest.getListId() + " does not exist for board "
          + taskRequest.getBoardId() + ".";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), responseMessage));
    }

    SuccessResponseMessage response = taskService.createNewTask(taskRequest);
    return ResponseEntity.created(null).body(response);
  }

  @PutMapping("/task/{id}")
  public ResponseEntity<?> updateTask(@PathVariable long id, @Valid @RequestBody UpdateTaskRequest taskRequest) {
    if (taskRepository.findById(id).isEmpty()) {
      String responseMessage = "Task with id " + id + " not found.";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), responseMessage));
    }

    SuccessResponseMessage response = taskService.updateTask(id, taskRequest);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/task/{id}")
  public ResponseEntity<?> deleteTask(@PathVariable long id) {
    if (taskRepository.findById(id).isEmpty()) {
      String responseMessage = "Task with id " + id + " not found.";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), responseMessage));
    }

    SuccessResponseMessage response = taskService.deleteTask(id);
    return ResponseEntity.ok(response);
  }

}
