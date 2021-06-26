package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.dto.ArticleDto;
import by.poshelyuk.blog.dto.converter.ArticleConverter;
import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.enums.Status;
import by.poshelyuk.blog.filtration.Page;
import by.poshelyuk.blog.filtration.impl.ArticleSortProvider;
import by.poshelyuk.blog.queries.ArticleQueryRepository;
import by.poshelyuk.blog.repository.ArticleRepository;
import by.poshelyuk.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleQueryRepository articleQueryRepository;
    private final ArticleConverter articleConverter;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleQueryRepository articleQueryRepository, ArticleConverter articleConverter) {
        this.articleRepository = articleRepository;
        this.articleQueryRepository = articleQueryRepository;
        this.articleConverter = articleConverter;
    }

    @Override
    public List<ArticleDto> getPublicArticle() {
        List<Article> articles = articleRepository.getAllByStatus(Status.PUBLIC);
        return articles.stream().map(articleConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void addArticle(ArticleDto articleDto) {
        articleRepository.save(articleConverter.convertToEntity(articleDto));
    }

    @Override
    public List<ArticleDto> findAll(Integer skip, Integer limit, String sort, String order) {
        List<Article> articles = articleQueryRepository.findAll(new Page(skip, limit), new ArticleSortProvider(sort, order));
        return articles.stream().map(articleConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Article findById(String id) {
        return (articleRepository.findById(id).get());
    }

    @Override
    public void update(ArticleDto articleDto) {
        articleRepository.save(articleConverter.convertToEntity(articleDto));
    }

    @Override
    public List<ArticleDto> findAllByUserEmail(String email) {
        List<Article> articles = articleRepository.findAllByUserEmail(email);
        return articles.stream().map(articleConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        articleRepository.deleteById(id);
    }
}
