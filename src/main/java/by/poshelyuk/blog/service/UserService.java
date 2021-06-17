package by.poshelyuk.blog.service;

import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.exception.UserAlreadyExistsException;

import java.util.List;

public interface UserService {

    User register(User user) throws UserAlreadyExistsException;

    List<User> getAll();

    User findByEmail(String email);

    User findById(String id);

}
