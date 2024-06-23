package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.service.BoardService;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boards")
    public ResponseEntity<List<Board>> getMembers() {
        List<Board> response = boardService.getAll();
        return ResponseEntity.ok(response);

    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<Board> getMember(@PathVariable Long id) {
        Board response = boardService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/boards")
    public ResponseEntity<Board> create(@RequestBody Board request) {
        Board response = boardService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id, @RequestBody Board request) {
        Board response = boardService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}