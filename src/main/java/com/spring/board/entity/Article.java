package com.spring.board.entity;

import jakarta.persistence.*;

@Entity
public class Article {
    @Id             // 엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 기능 추가
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    /* Article 생성자 추가 */
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    /* toString() 추가 */
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
