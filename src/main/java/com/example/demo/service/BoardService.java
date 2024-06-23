package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.exceptions.NullValueException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.dto.request.BoardCreateRequest;
import com.example.demo.controller.dto.request.BoardUpdateRequest;
import com.example.demo.controller.dto.response.BoardResponse;
import com.example.demo.domain.Board;

@Service
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardResponse> getBoards() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::from)
                .toList();
    }

    public BoardResponse getBoardById(Long id) {
        Board board = boardRepository.findById(id);
        return BoardResponse.from(board);
    }

    @Transactional
    public BoardResponse createBoard(BoardCreateRequest request) {
        if (request.name() == null) {
            ArrayList<String> nullList = null;
            nullList.add("게시판 이름");
            throw new NullValueException("요청에 필요한 항목이 누락됐습니다.", nullList);
        }

        Board board = new Board(request.name());
        Board saved = boardRepository.insert(board);
        return BoardResponse.from(saved);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id);

        boardRepository.deleteById(id);
    }

    @Transactional
    public BoardResponse update(Long id, BoardUpdateRequest request) {
        Board board = boardRepository.findById(id);
        if (request.name() == null) {
            ArrayList<String> nullList = new ArrayList<>();
            nullList.add("게시판 이름");
            throw new NullValueException("요청에 필요한 항목이 누락됐습니다.", nullList);
        }

        board.update(request.name());
        Board updated = boardRepository.update(board);
        return BoardResponse.from(updated);
    }
}
