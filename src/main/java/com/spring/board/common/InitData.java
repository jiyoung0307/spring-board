package com.spring.board.common;

import com.spring.board.entity.Article;
import com.spring.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData implements ApplicationListener<ApplicationReadyEvent> {

    private final ArticleRepository articleRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(null, "가가가", "1111"));
        articles.add(new Article(null, "나나나", "2222"));
        articles.add(new Article(null, "다다다", "3333"));

        // 데이터베이스에 저장
        articleRepository.saveAll(articles);
    }
}
