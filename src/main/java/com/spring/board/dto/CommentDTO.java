/*
package com.spring.board.dto;

import com.spring.board.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDTO {
    private Long id;    // 댓글의 id
    private Long articleId; // 댓글의 부모 id
    private String nickname; // 댓글 작성자
    private String body; // 댓글 본문

    public static CommentDTO createCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),    // 댓글 엔티티의 id
                comment.getArticle().getId(),   // 댓글 엔티티가 속한 부모 게시글의 id
                comment.getNickname(),  // 댓글 엔티티의 nickname
                comment.getBody()   // 댓글 엔티티의 body
        );
    }
}
*/
