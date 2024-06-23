package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Board;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    public Board getById(Long id) {
        return boardRepository.findById(id);
    }

    public Board create(Board board) {
        return boardRepository.save(board);
    }

    @Transactional
    public Board update(Long id, Board board) {
        board.setId(id);
        return boardRepository.update(id, board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}