package by.poshelyuk.blog.dao.impl;

import by.poshelyuk.blog.dao.ArticleDAO;
import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Tag;
import by.poshelyuk.blog.entity.enums.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ArticleDAOImpl implements ArticleDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public void save(Article article) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(article);
    }

    @Override
    public Article findById(String id) {
        Session session = sessionFactory.getCurrentSession();
        Article article = session.get(Article.class, id);
        return article;
    }

    @Override
    public void deleteById(String id) {
        Session session = sessionFactory.getCurrentSession();

        Query<Article> query = session.createQuery("delete from Article a where a.articleId=:id");
        query.executeUpdate();
    }

    @Override
    public List<Article> getAllByStatus(Status articleStatus) {
        Session session = sessionFactory.getCurrentSession();
        Query<Article> query = session.createQuery("FROM Article a WHERE a.status=:articleStatus");
        query.setParameter("articleStatus", articleStatus);
        return query.getResultList();

    }

    @Override
    public List<Article> getAllByTag(Tag tag) {
        Session session = sessionFactory.getCurrentSession();
        Query<Article> query = session.createQuery("FROM Article a where :tag MEMBER a.tags");
        query.setParameter("tag", tag);
        return query.getResultList();
    }
}
