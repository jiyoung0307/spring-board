package com.spring.board.service;

import com.spring.board.dto.ArticleFormDTO;
import com.spring.board.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;  // Test 패키지 임포트
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest     // 해당 클래스를 스프링 부트와 연동해 테스트
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;  // articleService 객체 주입

    @Test   // 해당 메서드가 테스트 코드임을 선언
    void index() {
        /* 1. 예상 데이터 작성하기 */
        /* 1-1. 예상 데이터 객체로 저장 */
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));  // a,b,c 합치기

        /* 2. 실제 데이터 획득하기 */
        List<Article> articles = articleService.index();

        /* 3. 예상 데이터와 실제 데이터 비교해 검증하기 */
        /* assertEquals(x, y) : x 예상데이터 / y 실제 데이터 */
        assertEquals(expected.toString(), articles.toString());

    }

    @Test
    void show_성공_존재하는_id_입력() {
        /* 1. 예상 데이터 */
        Long id = 1L;
        Article expected = new Article(1L, "가가가가", "1111");     // 예상 데이터 저장

        /* 2. 실제 데이터 */
        Article article = articleService.show(id);  // 실제 데이터 저장

        /* 3. 비교 및 검증 */
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하는_id_입력() {
        /* 1. 예상 데이터 */
        Long id = -1L;
        Article expected = null;

        /* 2. 실제 데이터 */
        Article article = articleService.show(id);
        /* 3. 비교 및 검증 */
        assertEquals(expected, article);
    }

    @Transactional
    @Test
    void create_성공_title과_content만_있는_DTO_입력() {
        /* 1. 예상 데이터 */
        String title = "라라라라";  // title과 content 값 임의 배정
        String content = "4444";

        ArticleFormDTO articleFormDTO = new ArticleFormDTO(null, title, content);   // DTO 생성
        Article expected = new Article(4L, title, content); // 예상 데이터 저장

        /* 2. 실제 데이터 */
        Article article = articleService.create(articleFormDTO);    // 실제 데이터 저장

        /* 3. 비교 및 검증 */
        assertEquals(expected.toString(), article.toString());  // 비교
    }

    @Transactional
    @Test
    void create_실패_id가_포함된_DTO_입력() {
        /* 1. 예상 데이터 */
        Long id = 4L;
        String title = "라라라라";  // title과 content 값 임의 배정
        String content = "4444";

        ArticleFormDTO articleFormDTO = new ArticleFormDTO(id, title, content);   // DTO 생성
        Article expected = null;  // 예상 데이터 저장

        /* 2. 실제 데이터 */
        Article article = articleService.create(articleFormDTO);    // 실제 데이터 저장

        /* 3. 비교 및 검증 */
        assertEquals(expected, article);
    }
}