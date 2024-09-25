package com.spring.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor  // Article() 생성자를 대체하는 어노테이션 추가
@NoArgsConstructor   // 기본 생성자 추가 어노테이션 추가
@ToString            // toString() 메서드를 대체하는 어노테이션 추가
@Data                // Getter Setter
@Entity
public class Article {
    @Id   // 엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 기능 추가
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

}
