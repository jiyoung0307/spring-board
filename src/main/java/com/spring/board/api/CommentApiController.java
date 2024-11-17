package com.spring.board.api;

import com.spring.board.dto.CommentDTO;
import com.spring.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/articles/{articleId}/comments")  // 댓글 생성 요청 접수
    public ResponseEntity<CommentDTO> create(@PathVariable Long articleId, @RequestBody CommentDTO commentDTO) {   // create() 생성
        /* 서비스에 위임 */
        CommentDTO createdDTO = commentService.create(articleId, commentDTO);
        /* 결과 응답 */
        return ResponseEntity.status(HttpStatus.OK).body(createdDTO);
    }
    /* 3. 댓글 수정 */
    @PatchMapping("/comments/{id}") // 댓글 수정 요청 접수
    public ResponseEntity<CommentDTO> update(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {   // update() 생성
        /* 서비스에 위임 */
        CommentDTO updatedDTO = commentService.update(id, commentDTO);
        /* 결과 응답 */
        return ResponseEntity.status(HttpStatus.OK).body(updatedDTO);
    }
    /* 4. 댓글 삭제 */
}