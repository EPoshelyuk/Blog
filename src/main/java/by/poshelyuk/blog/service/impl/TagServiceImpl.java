package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.dao.ArticleDAO;
import by.poshelyuk.blog.dao.TagDAO;
import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Tag;
import by.poshelyuk.blog.service.TagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagDAO tagDAO;
    private final ArticleDAO articleDAO;

    public TagServiceImpl(TagDAO tagDAO, ArticleDAO articleDAO) {
        this.tagDAO = tagDAO;
        this.articleDAO = articleDAO;
    }


    @Override
    public List<Article> getArticlesByTags(List<String> tagNames) {

        List<Tag> tags = tagNames.stream()
                .map(tagDAO::findByName)
                .collect(Collectors.toList());

        List<Article> articles = new ArrayList<>();
        for (Tag tag : tags) {
            List<Article> allByTag = articleDAO.getAllByTag(tag);
            articles.addAll(allByTag);
        }
        return articles;

    }

    @Override
    public Map<String, Integer> getTagCloud() {

        List<Tag> tags = tagDAO.findAll();

        int count;
        HashMap<String, Integer> tagCloud = new HashMap<>();

        for (Tag tag : tags) {
            count = tag.getArticles().size();
            tagCloud.put(tag.getName(), count);
        }
        return tagCloud;
    }

    public TagDAO getTagDAO() {
        return tagDAO;
    }
}
