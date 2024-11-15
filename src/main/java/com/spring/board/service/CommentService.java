package com.spring.board.service;

import com.spring.board.dto.CommentDTO;
import com.spring.board.entity.Comment;
import com.spring.board.repository.ArticleRepository;
import com.spring.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service    // 서비스로 선언
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;    // 댓글 리파지터리 객체 주입
    @Autowired
    private ArticleRepository articleRepository;    // 게시글 리파지터리 객체 주입

    public List<CommentDTO> comments(Long articleId) {
        /* 1. 댓글 조회 */
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        /* 2. 엔티티 -> DTO 변환 */
        List<CommentDTO> commentDTO = new ArrayList<CommentDTO>();
        for (int i = 0; i < comments.size(); i++) {     // 조회한 댓글 엔티티 수만큼 반복하기
            Comment comment = comments.get(i);  // 조회한 댓글 엔티티 하나씩 가져오기
            CommentDTO dto = CommentDTO.createCommentDTO(comment);  // 엔티티를 DTO로 변환
            commentDTO.add(dto);  // 변환한 dto를 commentDTO 리스트에 삽입
        }
        /* 3. 결과 반환 */
        return commentDTO;
    }
}