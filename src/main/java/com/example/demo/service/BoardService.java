package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Board;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    @Transactional
    public Board create(Board board) {
        return boardRepository.save(board);
    }

    @Transactional
    public Board update(Board board) {
        return boardRepository.save(Board board);
    }

    public void delete(Board board) {
        boardRepository.delete(board);
    }
}