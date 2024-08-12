package ru.egartech.Services;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.egartech.Models.User;

@Service("emailSenderService")
public class EmailSenderService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    @Async
    public void sendEmailTask(User user) throws AddressException {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Кончается время на выполнение задачи");
            mailMessage.setFrom(String.valueOf(new InternetAddress("daudmammaev@yandex.ru")));
            mailMessage.setText("Выполните задачу");
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            //
        }
    }

    @Async
    public void sendEmailCalendarPost(User user) throws AddressException {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Завтра мероприятие");
            mailMessage.setFrom(String.valueOf(new InternetAddress("daudmammaev@yandex.ru")));
            mailMessage.setText("Завтра мероприятие");
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            //
        }
    }
}
