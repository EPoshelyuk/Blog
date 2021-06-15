package by.poshelyuk.blog.dao.impl;

import by.poshelyuk.blog.dao.CommentDAO;
import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Comment> getCommentByArticleId(String id) {
        Session session = sessionFactory.getCurrentSession();

        Query<Comment> query = session.createQuery("FROM Comment c WHERE c.article.id=:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Comment getByCommentIdAndArticleId(String commentId, String articleId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Comment> query = session.createQuery("FROM Comment c where c.id=:commentId AND c.article.id=:articleId");
        query.setParameter("commentId", commentId);
        query.setParameter("articleId", articleId);
        return query.uniqueResult();
    }

    @Override
    public void addComment(Comment comment) {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(comment);
        }
    }

