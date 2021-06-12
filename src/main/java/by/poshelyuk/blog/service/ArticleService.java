package by.poshelyuk.blog.service;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.enums.Status;
import javassist.NotFoundException;

import java.util.List;

public interface ArticleService {

    void updateArticle(Article article);

    void addArticle(Article article);

    List<Article> getAll();

    Article getById(String id) throws NotFoundException;

    void delete(String id);

    List<Article> getPublicArticle();
}