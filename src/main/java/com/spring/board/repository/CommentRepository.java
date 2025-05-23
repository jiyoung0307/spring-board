package com.spring.board.repository;

import com.spring.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /* 특정 게시글의 모든 댓글 조회 */
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true) // value 속성에 실행하려는 쿼리 작성
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    @Query("SELECT c FROM Comment c WHERE c.nickname = :nickname")
    /* 특정 닉네임의 모든 댓글 조회 */
    List<Comment> findByNickname(@Param("nickname") String nickname);
}
