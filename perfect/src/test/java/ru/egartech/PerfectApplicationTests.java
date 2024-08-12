package ru.egartech;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.egartech.Dto.RegDto;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.Repository.DishRepository;
import ru.egartech.Repository.UserRepository;
import ru.egartech.Services.DishServicesImpl;
import ru.egartech.Services.UserServices.UserServices;
import ru.egartech.Models.CalendarPost;
import ru.egartech.Models.Dish;
import ru.egartech.Models.User;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PerfectApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    private static final ObjectMapper om = new ObjectMapper();
    @MockBean
    private DishRepository dishRepository;
    @MockBean
    private DishServicesImpl dishServicesImpl;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserServices userServices;
    @MockBean
    private CalendarPostRepository calendarPostRepository;

    @Test
    public void TestUsers() throws Exception {
        mockMvc.perform(
                        get("/rest/users"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void TestUser() throws Exception {
        long id = 32;
        mockMvc.perform(
                        get("/rest/user/{id}", id))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void TestUpdateUser() throws Exception {
        User user = new User();
        user.setName("waw");
        long id = 34;

        if (userRepository.findById(id).isPresent()) {
            when(userServices.userUpdate(userRepository.findById(id).get(), user)).thenReturn(user);
            mockMvc.perform(
                            post("/rest/update/user/{id}", id)
                                    .content(om.writeValueAsString(user))
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name").value("waw"));
        }
    }

    @Test
    public void TestAddDish() throws Exception {
        Dish dish = new Dish();
        dish.setName("name");

        when(dishServicesImpl.save(dish)).thenReturn(dish);

        mockMvc.perform(
                        post("/rest/add-dish")
                                .content(om.writeValueAsString(dish))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void TestAllMenu() throws Exception {
        Dish dish = new Dish();
        Dish dish1 = new Dish();
        List<Dish> list = new ArrayList<>();
        list.add(dish1);
        list.add(dish);

        when(dishRepository.findAll()).thenReturn(list);

        mockMvc.perform(
                        get("/rest/all-menu")
                                .content(om.writeValueAsString(dish))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

    }

    @Test
    public void TestRegistration() throws Exception {
        RegDto registrationDto = new RegDto();
        registrationDto.setLogin("qwwww");
        registrationDto.setPassword("qwwww");
        User user = new User();
        user.setLogin("qwwww");
        user.setPassword("qwwww");

        when(userServices.userRegistration(registrationDto)).thenReturn(user);

        mockMvc.perform(
                        post("/rest/registration")
                                .content(om.writeValueAsString(registrationDto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void TestAllCalendarPost() throws Exception {
        CalendarPost calendarPost = new CalendarPost();
        CalendarPost calendarPost1 = new CalendarPost();
        List<CalendarPost> list = new ArrayList<>();
        list.add(calendarPost);
        list.add(calendarPost1);

        when(calendarPostRepository.findAll()).thenReturn(list);

        mockMvc.perform(
                        get("/rest/all-menu")
                                .content(om.writeValueAsString(list))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

    }
}
