package ru.egartech.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.Dto.CalendarPostDto;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.models.CalendarPost;
import ru.egartech.models.User;

import java.util.Date;
import java.util.List;

@Service
public class IventServices {
    @Autowired
    public CalendarPostRepository calendarPostRepository;
    @Autowired
    public UserRepository userRepository;

    public List<CalendarPost> iventCheckForDeleteTime(User user) {
        Date date = new Date();
        List<CalendarPost> list = calendarPostRepository.findAllByUser(user).get();
        list.forEach(e -> {
            if (e.getDateDelete().getTime() < date.getTime()) {
                list.remove(e);
            }
        });
        return list;
    }

    /**
     * Пояснение:
     * accept - это список, который приходит к нам с чекпоинтами тех кто пришел, а далее мы находим через id наш пост
     * и удаляем в списке ListVisitUser людей, которые отммечены чекпоинтами, а в список ListForCheckUser добавляем
     **/
    public void iventAddUserToListForCheckUserAndDeleteUserFromListVisitUser(long id, CalendarPost accept) {
        int i = 0;
        CalendarPost accept1 = calendarPostRepository.findById(id).get();
        while (i < accept.getListVisitUser().size()) {
            if (accept.getListVisitUser().get(i).isCheckIvent()) {
                accept.getListForCheckUser().add(userRepository.findByLogin(accept.getListVisitUser().get(i).getLogin()).get());
                accept1.getListForCheckUser().add(userRepository.findByLogin(accept.getListVisitUser().get(i).getLogin()).get());
                calendarPostRepository.save(accept1);
            }
            i++;
        }
        for (int p = 0; p < accept1.getListVisitUser().size(); p++) {
            for (int j = 0; j < accept1.getListForCheckUser().size(); j++) {
                if (accept1.getListVisitUser().get(p) == accept1.getListForCheckUser().get(j)) {
                    accept1.getListVisitUser().remove(userRepository.findByLogin(accept1.getListVisitUser().get(p).getLogin()).get());
                    calendarPostRepository.save(accept1);
                }
            }
        }
    }
}
