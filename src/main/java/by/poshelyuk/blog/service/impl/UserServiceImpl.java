package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.dao.UserDAO;
import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.exception.UserAlreadyExistsException;
import by.poshelyuk.blog.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public User register(User user) throws UserAlreadyExistsException {
        if (userDAO.findByEmail(user.getEmail()) == null) {
            throw new UserAlreadyExistsException(user.getEmail());
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDAO.register(user);

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User findById(String id) {
        return userDAO.findById(id);
    }

}
