package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.dao.ArticleDAO;
import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.enums.Status;
import by.poshelyuk.blog.exception.ArticleNotFoundException;
import by.poshelyuk.blog.service.ArticleService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDAO articleDAO;

    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    @Transactional
    public void updateArticle(Article article) {

        articleDAO.save(article);
    }

    @Override
    @Transactional
    public void addArticle(Article article) {

        articleDAO.save(article);
    }

    @Override
    @Transactional
    public Article getById(String id) throws NotFoundException {

        return articleDAO.findById(id);
    }

    @Override
    @Transactional
    public void delete(String id) {

        articleDAO.deleteById(id);
    }

    @Override
    @Transactional
    public List<Article> getPublicArticle() {

        List<Article> allByStatus = articleDAO.getAllByStatus(Status.PUBLIC);
        System.out.println(allByStatus);
        return allByStatus;

    }
}
