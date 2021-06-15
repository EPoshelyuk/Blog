package by.poshelyuk.blog.service;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Tag;

import java.util.List;
import java.util.Map;

public interface TagService {

    List<Article> getArticlesByTags(List<String> tagNames);

    Map<String, Integer> getTagCloud();
}
