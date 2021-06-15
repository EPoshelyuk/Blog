package by.poshelyuk.blog.dao.impl;

import by.poshelyuk.blog.dao.TagDAO;
import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Comment;
import by.poshelyuk.blog.entity.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDAOImpl implements TagDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Tag findByName(String tagName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Tag> query = session.createQuery("FROM Tag t where t.name=:tagName");
        query.setParameter("tagName", tagName);
        return query.uniqueResult();
    }

    @Override
    public List<Tag> findAll() {
        return null;
    }
}
