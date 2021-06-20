package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.entity.enums.Role;
import by.poshelyuk.blog.exception.ActivationCodeNotFoundException;
import by.poshelyuk.blog.exception.UserAlreadyExistsException;
import by.poshelyuk.blog.repository.UserRepository;
import by.poshelyuk.blog.service.EmailService;
import by.poshelyuk.blog.service.RedisService;
import by.poshelyuk.blog.service.RegistrationService;
import jdk.jfr.consumer.RecordingFile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {


    private final String LINK = "http://localhost:8080/simple-blog/auth/confirm/%s/%s";

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final RedisService redisService;

    public RegistrationServiceImpl(EmailService emailService, UserRepository userRepository, RedisService redisService) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.redisService = redisService;
    }


    public void register(User user) throws UserAlreadyExistsException {
        User userFromDb = userRepository.findByEmail(user.getEmail());
        if (userFromDb != null) {
            throw new UserAlreadyExistsException(String.format("User with email %s already exist", user.getEmail()));
        }
        user.setEnabled(false);
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);

        String activationCode = UUID.randomUUID().toString();
        String message = String.format("Hello! Welcome to Blog. Please, visit next link " + LINK, user.getEmail(), activationCode);
        emailService.sendEmail(user.getEmail(), "Activation code", message);
        redisService.save(user.getEmail(), activationCode);
    }

    public boolean checkActivationCode(String email, String code) throws ActivationCodeNotFoundException {
        String byKey = redisService.findByKey(email);
        if (code.equals(byKey)) {
            return true;
        } else {
            throw new ActivationCodeNotFoundException(byKey);
        }
    }

}
