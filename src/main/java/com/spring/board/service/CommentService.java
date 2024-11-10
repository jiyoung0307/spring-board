package com.spring.board.service;

import com.spring.board.repository.ArticleRepository;
import com.spring.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    // 서비스로 선언
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;    // 댓글 리파지터리 객체 주입
    @Autowired
    private ArticleRepository articleRepository;    // 게시글 리파지터리 객체 주입
}