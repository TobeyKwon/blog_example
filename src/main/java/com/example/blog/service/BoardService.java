package com.example.blog.service;

import com.example.blog.entity.Board;
import com.example.blog.entity.Reply;
import com.example.blog.entity.User;
import com.example.blog.repository.BoardRepository;
import com.example.blog.repository.ReplyRepository;
import com.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public Integer savePost(Board board, Integer userId) {
        User author = userRepository.getById(userId);
        board.setAuthor(author);
        board.setCount(0);
        return boardRepository.save(board).getId();
    }

    @Transactional(readOnly = true)
    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public Board showDetail(Integer id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 글 아이디를 찾을 수 없습니다.");
                });
        board.increamentCount();
        System.out.println("board = " + board);
        return board;
    }

    @Transactional
    public void delete(Integer id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void udpate(Integer id, Board udpateParam) {
        Board find = boardRepository.findById(id).
                orElseThrow(() -> {
                    return new IllegalArgumentException("해당 ID가 존재하지 않습니다.");
                });
        find.setTitle(udpateParam.getTitle());
        find.setContent(udpateParam.getContent());
    }

    @Transactional
    public void saveReply(Integer boardId, Reply reply, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("댓글 쓰기 실패: 게시글 id를 찾을 수 없습니다.");
                });
        reply.setUser(user);
        reply.setBoard(board);

        replyRepository.save(reply);
    }

    public void deleteReply(Integer replyId) {
        replyRepository.deleteById(replyId);
    }
}
