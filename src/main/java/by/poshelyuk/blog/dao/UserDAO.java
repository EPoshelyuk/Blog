package by.poshelyuk.blog.dao;

import by.poshelyuk.blog.entity.User;

import java.util.List;

public interface UserDAO {

    User register(User user);

    List<User> getAll();

    User findByEmail(String email);

    User findById(String id);

}
