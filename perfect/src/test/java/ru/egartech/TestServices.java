package ru.egartech;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.Services.CalendarPostServices;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.Menu;
import ru.egartech.models.User;

import java.text.ParseException;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TestServices {
    @MockBean
    private CalendarPostServices calendarPostServices;
    @Mock
    private CalendarPostRepository calendarPostRepository;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void test_method_from_calendar_services_addCalendarPost() {
        User user = new User();
        CalendarPost calendarPost = new CalendarPost();
        Menu menu = new Menu();
        Date date = new Date();

        user.setLogin("123");
        calendarPost.setUser(user);
        calendarPost.setMenu(menu);
        calendarPost.setDateCreate(date);
        calendarPost.setText("Hello");

        when(calendarPostRepository.save(any(CalendarPost.class))).thenReturn(calendarPost);


        assertEquals(calendarPost.getUser().getLogin(), "123");
    }



}
