package com.spring.board.service;

import com.spring.board.dto.ArticleFormDTO;
import com.spring.board.entity.Article;
import com.spring.board.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class ArticleTransactionService {

    @Autowired
    private ArticleRepository articleRepository;    // 게시글 리파지터리 객체 주입

    public List<Article> createArticles(List<ArticleFormDTO> articleFormDTOs) {
        /* 1. DTO 묶음을 엔티티 묶음으로 변환하기 */
        List<Article> articleList = articleFormDTOs.stream()    // articleFormDTOs를 스트림화 / 최종 결과를 articleList에 저장
                .map(articleFormDTO -> articleFormDTO.toEntity())   // map()으로 articleFormDTO가 하나씩 올 때마다 articleFormDTO.toEntity()를 수행해 매핑함
                .collect(Collectors.toList());  // 매핑한 것을 리스트로 묶음

        /* 2. 엔티티 묶음을 DB에 저장하기 */
        articleList.stream()    // articleList를 스트림화
                .forEach(article -> articleRepository.save(article));   // article이 하나씩 올 때마다 articleRepository를 통해 DB에 저장

        /* 3. 강제로 예외 발생시키기 */
        articleRepository.findById(-1L)     // id가 -1인 데이터 찾기
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!!"));    // 찾는 데이터가 없으면 예외 발생

        /* 4. 결과 값 반환하기 */
        return articleList;
    }
}
