package programmer_shinobi.webmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PartnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPartner() throws Exception {
        mockMvc.perform(
                get("/partner/current")
                        .header("X-API-KEY", "SAMPLE")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("SAMPLE : Sample Partner"))
        );
    }

}