package by.poshelyuk.blog.service;

import by.poshelyuk.blog.entity.Article;

import java.util.List;
import java.util.Map;

public interface TagService {

    List<Article> getArticlesByTagsNames(List<String> tagNames);

    Map<String, Integer> getTagCloud();
}
