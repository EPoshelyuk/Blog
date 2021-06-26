package by.poshelyuk.blog.service;

import by.poshelyuk.blog.dto.ArticleDto;
import by.poshelyuk.blog.entity.Article;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> getPublicArticle();

    void addArticle(ArticleDto articleDto);

    List<ArticleDto> findAll(Integer skip, Integer limit, String sort, String order);

    Article findById(String id);

    void update(ArticleDto articleDto);

    List<ArticleDto> findAllByUserEmail(String email);

    void delete(String id);
}

