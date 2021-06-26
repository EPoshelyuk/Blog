package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.dto.ArticleDto;
import by.poshelyuk.blog.dto.converter.ArticleConverter;
import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Tag;

import by.poshelyuk.blog.repository.ArticleRepository;
import by.poshelyuk.blog.repository.TagRepository;
import by.poshelyuk.blog.service.TagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;

    public TagServiceImpl(TagRepository tagRepository, ArticleRepository articleRepository, ArticleConverter articleConverter) {
        this.tagRepository = tagRepository;
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
    }

    public List<ArticleDto> getArticlesByTagsNames(List<String> tagNames) {
        List<Tag> tags = tagNames.stream()
                .map(tagRepository::findByName)
                .collect(Collectors.toList());

        List<Article> articles = new ArrayList<>();
        tags.forEach(tag -> articles.addAll(articleRepository.getAllByTag(tag)));

        return articles.stream().map(articleConverter::convertToDto).collect(Collectors.toList());

    }

    public Map<String, Integer> getTagCloud() {
        List<Tag> tags = tagRepository.findAll();
        int count;
        HashMap<String, Integer> tagCloud = new HashMap<>();
        for (Tag tag : tags) {
            count = tag.getArticles().size();
            tagCloud.put(tag.getName(), count);
        }
        return tagCloud;
    }
}
