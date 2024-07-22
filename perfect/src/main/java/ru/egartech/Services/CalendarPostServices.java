package ru.egartech.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.Services.impl.UserServices;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.Menu;
import ru.egartech.models.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CalendarPostServices {
    @Autowired
    public CalendarPostRepository calendarPostRepository;
    @Autowired
    public UserServices userServices;
    public void addCalendarPost(CalendarPost calendarPost, User user) throws ParseException {
        CalendarPost calendarPost1 = new CalendarPost();
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        calendarPost1.setText(calendarPost.getText());
        calendarPost1.setDateCreate(date);
        calendarPost1.setDateDeleteString(calendarPost.getDateDeleteString());
        calendarPost1.setDateDelete(formatter.parse(calendarPost.getDateDeleteString()));
        calendarPost1.setUser(user);
        calendarPostRepository.save(calendarPost1);
    }
    public void addUserToCalendarPost(User user, CalendarPost calendarPost) throws Exception{
        try {
            if((calendarPost.getListVisitUser().contains(user))) {
                calendarPost.getListVisitUser().remove(user);
                calendarPost.getListVisitUser().add(user);
                calendarPostRepository.save(calendarPost);

            }else{
                calendarPost.getListVisitUser().add(user);
                calendarPostRepository.save(calendarPost);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }

    }
}
