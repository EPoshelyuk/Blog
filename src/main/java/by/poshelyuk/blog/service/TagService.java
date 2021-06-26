package by.poshelyuk.blog.service;

import by.poshelyuk.blog.dto.ArticleDto;

import java.util.List;
import java.util.Map;

public interface TagService {

    List<ArticleDto> getArticlesByTagsNames(List<String> tagNames);

    Map<String, Integer> getTagCloud();
}
