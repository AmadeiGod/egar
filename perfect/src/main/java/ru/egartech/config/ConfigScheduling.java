package ru.egartech.config;

import jakarta.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.Repository.TaskRepository;
import ru.egartech.Services.EmailSenderService;
import ru.egartech.Services.UserServices.UserServices;

import java.util.Date;
/**
 * emailSendTask - отправляет письмо на почту за день до дедлйна задачи
 * emailSendCalendarPost - отправляет письмо на почту за день мероприятия
 * **/
@Configuration
@EnableScheduling
public class ConfigScheduling {
    @Autowired
    public TaskRepository taskRepository;
    @Autowired
    public UserServices userServices;
    @Autowired
    public EmailSenderService emailSenderService;
    @Autowired
    public CalendarPostRepository calendarPostRepository;

    @Scheduled(fixedDelay = 1000)
    public void emailSendTask() throws InterruptedException {
        while (true) {
            Date date = new Date();
            taskRepository.findAll().stream().filter(
                    e -> (date.getTime() - e.getTimeToSolve().getTime()) < 86400000).forEach(e ->
            {
                try {
                    emailSenderService.sendEmailTask(e.getUserAccept());
                } catch (AddressException ex) {
                    throw new RuntimeException(ex);
                }
            });
            Thread.sleep(86400000); // 1 день
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void emailSendCalendarPost() throws InterruptedException {
        while (true) {
            Date date = new Date();
            calendarPostRepository.findAll().stream().filter(
                    e -> (date.getTime() - e.getDateDelete().getTime()) < 86400000).forEach(e ->
            {
                try {
                    e.getListVisitUser().forEach(k ->
                    {
                        try {
                            emailSenderService.sendEmailCalendarPost(k);
                        } catch (AddressException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                } finally {

                }
            });
            Thread.sleep(86400000); // 1 день
        }
    }
}
