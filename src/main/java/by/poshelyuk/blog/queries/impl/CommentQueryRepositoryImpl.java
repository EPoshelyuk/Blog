package by.poshelyuk.blog.queries.impl;

import by.poshelyuk.blog.entity.Comment;
import by.poshelyuk.blog.filtration.Page;
import by.poshelyuk.blog.filtration.impl.CommentSortProvider;
import by.poshelyuk.blog.queries.CommentQueryRepository;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@NoArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Comment> findAll(Page page, CommentSortProvider commentSortProvider) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);
        Root<Comment> root = criteriaQuery.from(Comment.class);
        criteriaQuery.orderBy(commentSortProvider.getSortOrder(root, criteriaBuilder));
        Integer skip = page.getSkip();
        Integer limit = page.getLimit();
        return sessionFactory.getCurrentSession()
                .createQuery(criteriaQuery)
                .setFirstResult((skip - 1) * limit)
                .setMaxResults(limit)
                .getResultList();
    }
}
