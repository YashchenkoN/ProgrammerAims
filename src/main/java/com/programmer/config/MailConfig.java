package com.programmer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by kolyan on 10/18/15.
 */
@Configuration
@PropertySource("classpath:settings.properties")
public class MailConfig {

    @Value("${mail.username}")
    private String mailUsername;

    @Value("${mail.password}")
    private String mailPassword;

    @Bean(name = "mailSender")
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        System.out.println(mailUsername);
        System.out.println(mailPassword);
        javaMailSender.setUsername(mailUsername);
        javaMailSender.setPassword(mailPassword);
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }
}
