package com.spring.board.entity;

import com.spring.board.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 해당 클래스가 엔티티임을 선언, 클래스 필드를 바탕으로 DB에 테이블 생성
@Data
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor  // 매개변수가 아예 없는 기본 생성자 자동 생성
public class Comment {

    @Id // 대표키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
    private Long id;    // 대표키

    @ManyToOne  // Comment 엔티티와 Article 엔티티를 다대일 관계로 설정
    @JoinColumn(name = "article_id")    // 외래키 생성, Article 엔티티의 기본키(id)와 매핑
    private Article article;    // 해당 댓글의 부모 게시물

    @Column(name = "nickname") // 해당 필드를 테이블의 속성으로 매핑
    private String nickname;    // 댓글을 단 사람

    @Column // 해당 필드를 테이블의 속성으로 매핑
    private String body;    // 댓글 본문

    public static Comment createComment(CommentDTO commentDTO, Article article) {
        /* 1. 예외 발생 */
        if (commentDTO.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");

        if (commentDTO.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");

        /* 2. 엔티티 생성 및 반환 */
        return new Comment(
                commentDTO.getId(), // 댓글 아이디
                article,    // 부모 게시글
                commentDTO.getNickname(),   // 댓글 닉네임
                commentDTO.getBody()    // 댓글 본문
        );
    }

    public void patch(CommentDTO commentDTO) {
        /* 1. 예외 발생 */
        if(this.id != commentDTO.getId())
            throw  new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다");
        /* 2. 객체 갱신 */
        if(commentDTO.getNickname() != null)    // 수정할 닉네임 데이터가 있다면
            this.nickname = commentDTO.getNickname();   // 내용 변경
        if(commentDTO.getBody() != null)    // 수정할 본문 데이터가 있다면
            this.body = commentDTO.getBody();   // 내용 반영
    }
}