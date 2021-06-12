package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.enums.Status;
import by.poshelyuk.blog.exception.ArticleNotFoundException;
import by.poshelyuk.blog.repository.ArticleRepository;
import by.poshelyuk.blog.service.ArticleService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void updateArticle(Article article) {

        articleRepository.save(article);
    }

    @Override
    public void addArticle(Article article) {

        articleRepository.save(article);
    }

    @Override
    public List<Article> getAll() {

        return articleRepository.findAll();
    }

    @Override
    public Article getById(String id) throws NotFoundException {

        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @Override
    public void delete(String id) {

        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> getPublicArticle() {
        return articleRepository.getAllByStatus(Status.PUBLIC);
    }
}
