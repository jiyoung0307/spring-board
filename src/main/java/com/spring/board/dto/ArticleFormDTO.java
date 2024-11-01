package com.spring.board.dto;

import com.spring.board.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleFormDTO {
    private Long id;        // id 필드 추가
    private String title;   // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    /* DTO -> Entity */
    public Article toEntity() {
        return new Article(id, title, content);
    }
}
