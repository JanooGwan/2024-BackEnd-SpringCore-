package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public String getAllBoards(Model model) {
        List<Board> boards = boardService.getAll();
        model.addAttribute("boards", boards);
        return "boardList";
    }

    @GetMapping("/{id}")
    public String getBoardById(@PathVariable Long id, Model model) {
        Board board = boardService.getById(id);
        model.addAttribute("board", board);
        return "board";
    }

    @PostMapping
    public String createOrUpdateBoard(@ModelAttribute Long id, Board board) {
        boardService.update(id, board);
        return "redirect:/boards";
    }

    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards";
    }
}