package com.spring.board.dto;

import com.spring.board.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleFormDTO {
    private String title;   // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    /* 전송받을 제목과 내용을 필드에 저장하는 생성자 추가 -> @AllArgsConstructor */
/*    public ArticleFormDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }*/

    /* 데이터를 잘 받았는지 확인할 toString() 추가 -> @ToString */
/*    @Override
    public String toString() {
        return "ArticleFormDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/

    /* DTO -> Entity */
    public Article toEntity() {
        return new Article(null, title, content);
    }
}
