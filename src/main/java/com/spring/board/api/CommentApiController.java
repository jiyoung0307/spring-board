package com.spring.board.api;

import com.spring.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController     // Rest 컨트롤러 선언
@RequestMapping("/api")
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    /* 1. 댓글 조회 */
    /* 2. 댓글 생성 */
    /* 3. 댓글 수정 */
    /* 4. 댓글 삭제 */
}