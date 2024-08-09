package ru.egartech;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.egartech.Repository.CalendarPostRepository;
import ru.egartech.Repository.DishRepository;
import ru.egartech.Repository.SendDishRepository;
import ru.egartech.Services.CalendarPostServices;
import ru.egartech.models.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    // Rest
    @Autowired
    private ObjectMapper objectMapper;
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
        user.setName("name");

        long id = 32;

        mockMvc.perform(
                        post("/rest/update/user/{id}", id)
                            .content(objectMapper.writeValueAsString(user))
                            .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("name"));

    }

}
