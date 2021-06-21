package by.poshelyuk.blog.service;

public interface EmailService {

    void sendEmail(String emailTo, String subject, String message);
}
