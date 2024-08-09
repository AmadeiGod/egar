package ru.egartech.Services;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.egartech.Controllers.AuthController;

@EnableScheduling
@AutoConfigureMockMvc
@SpringBootTest
public class AuthTest {
    private static final String NAME1 = "testing";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAuth() throws Exception {
        mockMvc.perform(
                        post("/auth/registration")
                                .param("login", NAME1)
                                .param("password", NAME1))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "http://localhost/auth/login"));
    }

}
