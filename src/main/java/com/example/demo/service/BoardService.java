package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.exceptions.NullValueException;
import com.example.demo.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.dto.request.BoardCreateRequest;
import com.example.demo.controller.dto.request.BoardUpdateRequest;
import com.example.demo.controller.dto.response.BoardResponse;
import com.example.demo.domain.Board;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    public Board getById(Long id) {
        return boardRepository.findById(id);
    }

    public Board create(Board board) {
        return boardRepository.save(board);
    }

    public Board update(Long id, Board board) {
        return boardRepository.save(board);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}