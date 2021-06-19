package by.poshelyuk.blog.queries.impl;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.filtration.Page;
import by.poshelyuk.blog.filtration.impl.ArticleSortProvider;
import by.poshelyuk.blog.queries.ArticleQueryRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@NoArgsConstructor
public class ArticleQueryRepositoryImpl implements ArticleQueryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Article> findAll(Page page, ArticleSortProvider articleSortProvider) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);
        criteriaQuery.orderBy(articleSortProvider.getSortOrder(root, criteriaBuilder));
        Integer skip = page.getSkip();
        Integer limit = page.getLimit();
        return entityManager.createQuery(criteriaQuery)
                .setFirstResult((skip - 1) * limit)
                .setMaxResults(limit)
                .getResultList();
    }
}


