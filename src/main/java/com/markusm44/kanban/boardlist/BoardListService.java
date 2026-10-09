package com.markusm44.kanban.boardlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.markusm44.kanban.boardlist.dto.CreateBoardListRequest;
import com.markusm44.kanban.boardlist.dto.UpdateBoardListRequest;
import com.markusm44.kanban.common.api.SuccessResponseMessage;
import com.markusm44.kanban.task.TaskRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BoardListService {
  @Autowired
  private BoardListRepository boardListRepository;

  @Autowired
  private TaskRepository taskRepository;

  public List<BoardList> getLists(long board_id) {
    List<BoardList> lists = boardListRepository.findByBoardId(board_id);
    return lists;
  }

  public SuccessResponseMessage createNewBoardList(CreateBoardListRequest boardListData) {
    BoardList newBoardList = new BoardList();

    newBoardList.setBoardId(boardListData.getBoardId());
    newBoardList.setName(boardListData.getName());

    BoardList savedBoardList = boardListRepository.save(newBoardList);

    String responseMessage = "New list created.";
    SuccessResponseMessage response = new SuccessResponseMessage(HttpStatus.CREATED.value(), responseMessage,
        savedBoardList.getId());

    return response;
  }

  public SuccessResponseMessage updateBoardList(long id, UpdateBoardListRequest boardListData) {
    BoardList boardList = boardListRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("List with id " + id + " not found."));

    if (boardListData.getName() != null) {
      boardList.setName(boardListData.getName());
    }
    boardListRepository.save(boardList);

    String responseMessage = "List updated successfully.";
    SuccessResponseMessage response = new SuccessResponseMessage(HttpStatus.OK.value(), responseMessage,
        boardList.getId());

    return response;
  }

  public SuccessResponseMessage deleteBoardList(long id) {
    taskRepository.deleteAllByListId(id);
    boardListRepository.deleteById(id);

    String responseMessage = "List and containing tasks deleted successfully.";
    SuccessResponseMessage response = new SuccessResponseMessage(HttpStatus.OK.value(), responseMessage,
        id);

    return response;
  }
}
