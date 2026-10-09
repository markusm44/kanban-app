package com.markusm44.kanban.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.markusm44.kanban.common.api.SuccessResponseMessage;
import com.markusm44.kanban.task.dto.CreateTaskRequest;
import com.markusm44.kanban.task.dto.UpdateTaskRequest;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {
  @Autowired
  private TaskRepository taskRepository;

  public List<Task> getTasks(long boardId) {
    List<Task> tasks = taskRepository.findByBoardId(boardId);
    return tasks;
  }

  public SuccessResponseMessage createNewTask(CreateTaskRequest taskData) {
    Task newTask = new Task();

    newTask.setBoardId(taskData.getBoardId());
    newTask.setListId(taskData.getListId());
    newTask.setName(taskData.getName());
    newTask.setDescription(taskData.getDescription() != null ? taskData.getDescription() : "");
    newTask.setTags(taskData.getTags() != null ? taskData.getTags() : "");
    newTask.setCompleted(false);

    Task savedTask = taskRepository.save(newTask);

    String responseMessage = "New task created.";
    SuccessResponseMessage response = new SuccessResponseMessage(HttpStatus.CREATED.value(), responseMessage,
        savedTask.getId());

    return response;
  }

  public SuccessResponseMessage updateTask(long id, UpdateTaskRequest taskData) {
    Task task = taskRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found."));

    if (taskData.getName() != null)
      task.setName(taskData.getName());
    if (taskData.getDescription() != null)
      task.setDescription(taskData.getDescription());
    if (taskData.isCompleted() != null)
      task.setCompleted(taskData.isCompleted());
    if (taskData.getTags() != null)
      task.setTags(taskData.getTags());

    taskRepository.save(task);

    String responseMessage = "Task updated successfully.";
    SuccessResponseMessage response = new SuccessResponseMessage(HttpStatus.OK.value(), responseMessage,
        task.getId());

    return response;
  }

  public SuccessResponseMessage deleteTask(long id) {
    taskRepository.deleteById(id);

    String responseMessage = "Task deleted successfully.";
    SuccessResponseMessage response = new SuccessResponseMessage(HttpStatus.OK.value(), responseMessage,
        id);

    return response;
  }
}
