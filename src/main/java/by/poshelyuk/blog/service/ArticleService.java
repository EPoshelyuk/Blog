package by.poshelyuk.blog.service;

import by.poshelyuk.blog.entity.Article;

import javassist.NotFoundException;

import java.util.List;

public interface ArticleService {

    List<Article> getPublicArticle();

    void addArticle(Article article);

    List<Article> findAll(Integer skip, Integer limit, String sort, String order);

}

