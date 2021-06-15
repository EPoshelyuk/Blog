package by.poshelyuk.blog.dao;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Tag;
import by.poshelyuk.blog.entity.enums.Status;

import java.util.List;
import java.util.Optional;

public interface ArticleDAO {
    void save(Article article);

    Article findById(String id);

    void deleteById(String id);

    List<Article> getAllByStatus(Status status);

    List<Article> getAllByTag(Tag tag);
}
