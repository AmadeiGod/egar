package ru.egartech.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.Repository.DishRepository;
import ru.egartech.models.Dish;

import java.util.List;

@Service
public class MenuServicesImpl implements MenuServices {
    @Autowired
    public DishRepository dishRepository;

    public List<Dish> menuUpdate(ru.egartech.models.Menu form) {
        List<Dish> list = form.getListDish();
        dishRepository.deleteAll();
        list.forEach(e -> {
            if (e.getCount() > 0) {
                dishRepository.save(e);
            }
        });
        return list;
    }
}
