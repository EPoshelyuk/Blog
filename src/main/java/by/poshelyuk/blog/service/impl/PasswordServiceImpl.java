package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.dto.AuthRequestDto;
import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.exception.ActivationCodeNotFoundException;
import by.poshelyuk.blog.service.EmailService;
import by.poshelyuk.blog.service.PasswordService;
import by.poshelyuk.blog.service.RedisService;
import by.poshelyuk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordServiceImpl implements PasswordService {

    private final String LINK = "http://localhost:8080/simple-blog/auth/reset/%s/%s";

    private final UserService userService;
    private final EmailService emailService;
    private final RedisService redisService;

    @Autowired
    public PasswordServiceImpl(UserService userService, EmailService emailService, RedisService redisService) {
        this.userService = userService;
        this.emailService = emailService;
        this.redisService = redisService;
    }

    @Override
    public boolean resetPassword(AuthRequestDto authRequestDto) {
        User user = (userService.findByEmail(authRequestDto.getEmail()));
        if (user != null && user.isEnabled()) {
            String activationCode = UUID.randomUUID().toString();
            String message = String.format("Hello! Please, visit next link " + LINK, user.getEmail(), activationCode);
            emailService.sendEmail(user.getEmail(), "New code", message);
            redisService.save(user.getEmail(), activationCode);
            emailService.sendEmail(user.getEmail(), user.getFirstName(), "reset password");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePassword(String email, String code, String newPassword) throws ActivationCodeNotFoundException {
        String key = redisService.findByKey(email);
        if (key.equals(code)) {
            User user = userService.findByEmail(email);
            user.setPassword(newPassword);
            userService.update(user);
            return true;
        } else {
            throw new ActivationCodeNotFoundException(code);
        }
    }
}

