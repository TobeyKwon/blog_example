package com.example.blog.controller.api;

import com.example.blog.entity.Board;
import com.example.blog.entity.User;
import com.example.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping("/api/board")
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Integer> saveForm(@RequestBody Board board, HttpSession session) {
        User user = (User)session.getAttribute("loginUser");
        return new ResponseEntity<>(boardService.savePost(board, user.getId()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody Board board) {
        System.out.println("1232board = " + board);
        boardService.udpate(id, board);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        System.out.println(id);
        boardService.delete(id);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
