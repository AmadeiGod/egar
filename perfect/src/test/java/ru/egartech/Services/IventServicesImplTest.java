package ru.egartech.Services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.User;

import java.util.Optional;

import static org.mockito.Mockito.when;

@DataJpaTest
@EnableScheduling
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IventServicesImplTest {
    @InjectMocks
    public IventServicesImpl iventServicesImpl;
    @Mock
    public CalendarPostRepository calendarPostRepository;
    @Mock
    public UserRepository userRepository;
    @Test
    public void iventAddUserToListForCheckUserAndDeleteUserFromListVisitUserTest(){
        CalendarPost accept = new CalendarPost();
        CalendarPost accept1 = new CalendarPost();
        long id = 1L;
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setLogin("1");
        user2.setLogin("2");
        user3.setLogin("3");
        accept1.getListVisitUser().add(user1);
        accept1.getListVisitUser().add(user2);
        accept1.getListVisitUser().add(user3);
        user1.setCheckIvent(true);
        user2.setCheckIvent(true);
        accept.getListVisitUser().add(user1);
        accept.getListVisitUser().add(user2);
        accept.getListVisitUser().add(user3);

        when(userRepository.findByLogin("1")).thenReturn(Optional.of(user1));
        when(userRepository.findByLogin("2")).thenReturn(Optional.of(user2));
        when(userRepository.findByLogin("3")).thenReturn(Optional.of(user3));
        when(calendarPostRepository.findById(1L)).thenReturn(Optional.of(accept1));

        if (calendarPostRepository.findById(1L).isPresent()){
            iventServicesImpl.iventAddUserToListForCheckUserAndDeleteUserFromListVisitUser(id, accept);
            Assertions.assertEquals(accept1.getListVisitUser().size(), 1);
        }


    }

}
