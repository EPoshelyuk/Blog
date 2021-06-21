package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.enums.Status;
import by.poshelyuk.blog.filtration.Page;
import by.poshelyuk.blog.filtration.impl.ArticleSortProvider;
import by.poshelyuk.blog.queries.ArticleQueryRepository;
import by.poshelyuk.blog.repository.ArticleRepository;
import by.poshelyuk.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleQueryRepository articleQueryRepository;


    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleQueryRepository articleQueryRepository) {
        this.articleRepository = articleRepository;
        this.articleQueryRepository = articleQueryRepository;
    }

    @Override
    public List<Article> getPublicArticle() {
        return articleRepository.getAllByStatus(Status.PUBLIC);
    }

    @Override
    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public List<Article> findAll(Integer skip, Integer limit, String sort, String order) {
        return articleQueryRepository.findAll(new Page(skip, limit), new ArticleSortProvider(sort, order));
    }

    @Override
    public Article findById(String id) {
        return articleRepository.findById(id).get();
    }

    @Override
    public void update(Article article) {
        articleRepository.save(article);
    }

    @Override
    public List<Article> findAllByUserEmail(String email) {
        return articleRepository.findAllByUserEmail(email);
    }

    @Override
    public void delete(String id) {
        articleRepository.deleteById(id);
    }
}
