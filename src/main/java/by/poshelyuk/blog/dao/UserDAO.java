package by.poshelyuk.blog.dao;

import by.poshelyuk.blog.entity.User;

import java.util.List;

public interface UserDAO {

    User save(User user);

    List<User> getAll();

    User findByEmailAndPassword(String email, String password);

    User findById(String id);

    User findByEmail(String email);
}
