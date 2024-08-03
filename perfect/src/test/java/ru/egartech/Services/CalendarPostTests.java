package ru.egartech.Services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.Menu;
import ru.egartech.models.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CalendarPostTests {
    @Mock
    private CalendarPostRepository calendarPostRepository;
    @InjectMocks
    private CalendarPostServices calendarPostServices;

    @Test
    public void test1() throws ParseException {

    }
    @Test
    public void testCalendarPostServicesAddCalendarPost() throws ParseException {

    }
}
