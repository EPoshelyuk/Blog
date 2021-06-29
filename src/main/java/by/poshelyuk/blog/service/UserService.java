package by.poshelyuk.blog.service;

import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.exception.UserAlreadyExistsException;

import java.util.List;

public interface UserService {

    User saveUser(User user) throws UserAlreadyExistsException;

    List<User> getAll();

    User findByEmailAndPassword(String email, String password);

    User findById(String id);

    User findByEmail(String email);

    void update(User user);
}
