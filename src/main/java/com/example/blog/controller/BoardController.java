package com.example.blog.controller;

import com.example.blog.entity.Board;
import com.example.blog.repository.BoardRepository;
import com.example.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/")
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String index(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("boards", boardService.boardList(pageable));
        return "index";
    }

    @GetMapping("/board/write")
    public String saveForm() {
        return "saveForm";
    }

    @GetMapping("/board/{id}")
    public String showDetail(@PathVariable Integer id, Model model) {
        Board board = boardService.showDetail(id);
        model.addAttribute("board", board);
        return "detail";
    }

    @GetMapping("/board/{id}/update")
    public String updateForm(@PathVariable Integer id, Model model) {
        Board board = boardService.showDetail(id);
        model.addAttribute("board", board);
        return "updateForm";
    }

}
