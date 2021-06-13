package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Tag;
import by.poshelyuk.blog.repository.ArticleRepository;
import by.poshelyuk.blog.repository.TagRepository;
import by.poshelyuk.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, ArticleRepository articleRepository) {
        this.tagRepository = tagRepository;
        this.articleRepository = articleRepository;
    }


    @Override
    public List<Article> getArticlesByTags(List<String> tagNames) {

        List<Tag> tags = tagNames.stream()
                .map(tagName -> tagRepository.findByName(tagName))
                .collect(Collectors.toList());

        List<Article> articles = new ArrayList<>();
        for (Tag tag : tags) {
            List<Article> allByTag = articleRepository.findAllByTag(tag);
            articles.addAll(allByTag);
        }
        return articles;

    }

    @Override
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
