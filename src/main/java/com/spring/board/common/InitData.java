package com.spring.board.common;

import com.spring.board.entity.Article;
import com.spring.board.entity.Coffee;
import com.spring.board.repository.ArticleRepository;
import com.spring.board.repository.CoffeeRepository;
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
    private final CoffeeRepository coffeeRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1L, "가가가가", "1111"));
        articles.add(new Article(2L, "나나나나", "2222"));
        articles.add(new Article(3L, "다다다다", "3333"));

        // 데이터베이스에 저장
        articleRepository.saveAll(articles);

        List<Coffee> coffee = new ArrayList<>();
        coffee.add(new Coffee(1L, "아메리카노",4100));
        coffee.add(new Coffee(2L, "라떼", 4600));
        coffee.add(new Coffee(3L, "모카", 5100));
        coffee.add(new Coffee(4L, "오늘의 커피", 3800));

        // 데이터베이스에 저장
        coffeeRepository.saveAll(coffee);
    }
}
