package ru.egartech.config;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.egartech.Repository.TaskRepository;
import ru.egartech.Services.EmailSenderService;
import ru.egartech.Services.impl.UserServices;
import ru.egartech.models.Task;
import ru.egartech.models.User;

import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class ConfigScheduling {
    @Autowired
    public TaskRepository taskRepository;
    @Autowired
    public UserServices userServices;
    @Autowired
    public EmailSenderService emailSenderService;


    public User req( Authentication authentication,HttpServletRequest request){
        return  userServices.userAuth(authentication,request);
    }
    @Scheduled(fixedDelay = 1000)
    public void emailSendTask() throws InterruptedException, AddressException {
        while (true){
            Date date = new Date();
            taskRepository.findAll().stream().filter(
                    e ->( date.getTime() - e.getTimeToSolve().getTime()) < 86400000).forEach(e->
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
}
