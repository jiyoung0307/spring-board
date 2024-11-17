package com.spring.board.service;

import com.spring.board.dto.CommentDTO;
import com.spring.board.entity.Article;
import com.spring.board.entity.Comment;
import com.spring.board.repository.ArticleRepository;
import com.spring.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service    // 서비스로 선언
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;    // 댓글 리파지터리 객체 주입
    @Autowired
    private ArticleRepository articleRepository;    // 게시글 리파지터리 객체 주입

    /* 댓글 조회 */
    public List<CommentDTO> comments(Long articleId) {
        /* 결과 반환 */
        return commentRepository.findByArticleId(articleId) // 댓글 엔티티 목록 조회
                .stream()   // 댓글 엔티티 목록을 스트림으로 변환
                .map(comment -> CommentDTO.createCommentDTO(comment))   // 엔티티를 DTO로 매핑
                .collect(Collectors.toList());  // 스트림을 리스트로 변환
    }

    /* 댓글 생성 */
    @Transactional
    public CommentDTO create(Long articleId, CommentDTO commentDTO) {
        /* 1. DB에서 부모 게시글을 조회해 가져오고 없을 경우 예외 발생시키기 */
        Article article = articleRepository.findById(articleId)     // 부모 게시글 가져오기
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " + "대상 게시글이 없습니다."));   // 없으면 에러 메세지 출력

        /* 2. 부모 게시글의 새 댓글 엔티티 생성하기 */
        Comment comment = Comment.createComment(commentDTO, article);

        /* 3. 생성된 엔티티를 DB에 저장하기 */
        Comment created = commentRepository.save(comment);

        /* 4. DB에 저장한 엔티티를 DTO로 변환해 반환하기 */
        return CommentDTO.createCommentDTO(created);
    }

    /* 댓글 수정 */
    @Transactional
    public CommentDTO update(Long id, CommentDTO commentDTO) {
        /* 1. 댓글 조회 및 예외 발생 */
        Comment target = commentRepository.findById(id)     // 수정할 댓글 가져오기
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" + "대상 댓글이 없습니다."));     // 없으면 에러 메세지 출력
        /* 2. 댓글 수정 */
        target.patch(commentDTO);
        /* 3. DB로 갱신 */
        Comment updated = commentRepository.save(target);
        /* 4. 댓글 엔티티를 DTO로 변환 및 반환 */
        return CommentDTO.createCommentDTO(updated);

    }
}