package by.poshelyuk.blog.service;

import by.poshelyuk.blog.entity.User;
import org.springframework.stereotype.Service;

public interface EmailService {

    void sendEmail(String emailTo, String subject, String message);
}
