package com.example.demo.controller;

import com.example.demo.controller.dto.request.BoardUpdateRequest;
import com.example.demo.controller.dto.response.BoardResponse;
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
import java.util.Optional;


@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardResponse>> getMembers() {
        List<BoardResponse> response = boardService.getAll();
        return ResponseEntity.ok(response);

    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<Optional<Board>> getMember(@PathVariable Long id) {
        Optional<Board> response = boardService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/boards")
    public ResponseEntity<BoardResponse> create(@RequestBody Board request) {
        BoardResponse response = boardService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<BoardResponse> update(@PathVariable Long id, @RequestBody BoardUpdateRequest request) {
        BoardResponse response = boardService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}