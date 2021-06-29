package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.entity.enums.Role;
import by.poshelyuk.blog.repository.UserRepository;
import by.poshelyuk.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        user.setRole(Role.ROLE_USER);
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with email: %s not found", email));
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UsernameNotFoundException(String.format("User with email: %s not found", email));
        }
        return user;
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }
}
