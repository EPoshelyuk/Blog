package by.poshelyuk.blog.service;

import by.poshelyuk.blog.entity.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getPublicArticle();

    void addArticle(Article article);

    List<Article> findAll(Integer skip, Integer limit, String sort, String order);

    Article findById(String id);

    void update(Article article);

    List<Article> findAllByUserEmail(String email);

    void delete(String id);
}

