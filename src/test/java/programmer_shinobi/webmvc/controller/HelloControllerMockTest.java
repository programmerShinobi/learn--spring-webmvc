package programmer_shinobi.webmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import programmer_shinobi.webmvc.service.HelloService;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HelloService helloService;

    @BeforeEach
    void setUp() {

        Mockito.when(helloService.hello(Mockito.anyString()))
                .thenReturn("Hello Everyone");

    }

    @Test
    void helloName() throws Exception {
        mockMvc.perform(
                get("/hello").queryParam("name", "Faqih")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Everyone"))
        );
    }

}
