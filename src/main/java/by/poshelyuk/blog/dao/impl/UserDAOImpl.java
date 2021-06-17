package by.poshelyuk.blog.dao.impl;

import by.poshelyuk.blog.dao.UserDAO;
import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Comment;
import by.poshelyuk.blog.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User register(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User u WHERE u.email=:email");
        query.setParameter("email", email);
        return query.uniqueResult();
    }

    @Override
    public User findById(String id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return user;
    }
}
