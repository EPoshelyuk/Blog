package by.poshelyuk.blog.service;

import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.exception.ActivationCodeNotFoundException;
import by.poshelyuk.blog.exception.UserAlreadyExistsException;

public interface RegistrationService {

    void register(User user) throws UserAlreadyExistsException;

    boolean checkActivationCode(String email, String code) throws ActivationCodeNotFoundException;
}
