package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.dao.UserDAO;
import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.entity.enums.Role;
import by.poshelyuk.blog.exception.UserAlreadyExistsException;
import by.poshelyuk.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserDAO userDAO;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User saveUser(User user) {
      // RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        user.setRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = findByEmail(email);
        if (result == null) {
            throw new UsernameNotFoundException(String.format("User with email: %s not found", email));
        }
        if (!passwordEncoder.matches(password, result.getPassword())) {
            log.error("IN findByEmailAndPassword - user by email: {} invalid password", email);
            throw new UsernameNotFoundException(String.format("User with email: %s not found", email));
        }
        return result;
    }

    @Override
    public User findById(String id) {
        return userDAO.findById(id);
    }

    public User findByEmail(String email) {
        User result = userDAO.findByEmail(email);
        if (result == null) {
            log.error("IN findByEmail - user by email: {} not found", email);
        }
        return result;
    }

}
