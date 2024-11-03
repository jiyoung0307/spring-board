package com.spring.board.common;

import com.spring.board.entity.Article;
import com.spring.board.entity.Coffee;
import com.spring.board.entity.Comment;
import com.spring.board.repository.ArticleRepository;
import com.spring.board.repository.CoffeeRepository;
import com.spring.board.repository.CommentRepository;
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
    private final CommentRepository commentRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1L, "가가가가", "1111"));
        articles.add(new Article(2L, "나나나나", "2222"));
        articles.add(new Article(3L, "다다다다", "3333"));

        articles.add(new Article(4L, "당신의 인생 영화는?", "댓글 고"));
        articles.add(new Article(5L, "당신의 소울 푸드는?", "댓글 고고"));
        articles.add(new Article(6L, "당신의 취미는?", "댓글 고고고"));

        // 저장한 Article 객체를 불러와서 사용
        Article article4 = articles.get(3);
        Article article5 = articles.get(4);
        Article article6 = articles.get(5);


        // 데이터베이스에 저장
        articleRepository.saveAll(articles);

        List<Coffee> coffee = new ArrayList<>();
        coffee.add(new Coffee(1L, "아메리카노",4100));
        coffee.add(new Coffee(2L, "라떼", 4600));
        coffee.add(new Coffee(3L, "모카", 5100));
        coffee.add(new Coffee(4L, "오늘의 커피", 3800));

        // 데이터베이스에 저장
        coffeeRepository.saveAll(coffee);

        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1L, article4, "Park", "굿 윌 헌팅"));
        comments.add(new Comment(2L, article4, "Kim", "아이 엠 샘"));
        comments.add(new Comment(3L, article4, "Choi", "쇼생크 탈출"));

        comments.add(new Comment(4L, article5, "Park", "치킨"));
        comments.add(new Comment(5L, article5, "Kim", "샤브샤브"));
        comments.add(new Comment(6L, article5, "Choi", "초밥"));

        comments.add(new Comment(7L, article6, "Park", "조깅"));
        comments.add(new Comment(8L, article6, "Kim", "유튜브 시청"));
        comments.add(new Comment(9L, article6, "Choi", "독서"));

        // 데이터베이스에 저장
        commentRepository.saveAll(comments);

    }
}
