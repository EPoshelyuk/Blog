package by.poshelyuk.blog.queries.impl;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.filtration.Page;
import by.poshelyuk.blog.filtration.impl.ArticleSortProvider;
import by.poshelyuk.blog.queries.ArticleQueryRepository;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.*;
import java.util.List;

@Repository
@NoArgsConstructor
public class ArticleQueryRepositoryImpl implements ArticleQueryRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Article> findAll(Page page, ArticleSortProvider articleSortProvider) {

        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);
        criteriaQuery.orderBy(articleSortProvider.getSortOrder(root, criteriaBuilder));
        Integer skip = page.getSkip();
        Integer limit = page.getLimit();
        return sessionFactory.getCurrentSession()
                .createQuery(criteriaQuery)
                .setFirstResult((skip - 1) * limit)
                .setMaxResults(limit)
                .getResultList();
    }
}


