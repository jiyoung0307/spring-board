package com.spring.board.api;

import com.spring.board.dto.CommentDTO;
import com.spring.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController     // Rest 컨트롤러 선언
@RequestMapping("/api")
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    /* 1. 댓글 조회 */
    @GetMapping("/articles/{articleId}/comments")   // 댓글 조회 요청 접수
    public ResponseEntity<List<CommentDTO>> comments(@PathVariable Long articleId) {
        /* 서비스에 위임 */
        List<CommentDTO> commentDTO = commentService.comments(articleId);
        /* 결과 응답 */
        return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
    }
    /* 2. 댓글 생성 */
    /* 3. 댓글 수정 */
    /* 4. 댓글 삭제 */
}