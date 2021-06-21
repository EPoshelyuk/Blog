package by.poshelyuk.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@PropertySource(value = "classpath:application.properties")
@Configuration
public class EmailConfig {

    private static final String HOST = "spring.mail.host";
    private static final String USERNAME = "spring.mail.username";
    private static final String PASSWORD = "spring.mail.password";
    private static final int PORT = Integer.parseInt("spring.mail.port");
    private static final String PROTOCOL = "spring.mail.protocol";
    private static final String DEBUG = "mail.debug";

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(HOST);
        mailSender.setPort(PORT);
        mailSender.setUsername(USERNAME);
        mailSender.setPassword(PASSWORD);

        Properties properties = mailSender.getJavaMailProperties();

        properties.setProperty("mail.transport.protocol", PROTOCOL);
        properties.setProperty("mail.debug", DEBUG);

        return mailSender;
    }
}
