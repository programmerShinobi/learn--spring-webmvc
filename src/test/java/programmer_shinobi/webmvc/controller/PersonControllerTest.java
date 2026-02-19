package programmer_shinobi.webmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPerson() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Faqih")
                        .param("middleName", "Pratama")
                        .param("lastName", "Muhti")
                        .param("email", "faqih@example.com")
                        .param("phone", "0123456789")
                        .param("address.street", "Jalan Seroja Raya")
                        .param("address.city", "Bogor")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "123456")
                        .param("hobbies[0]", "Coding")
                        .param("hobbies[1]", "Reading")
                        .param("hobbies[2]", "Jogging")
                        .param("socialMedia[0].name", "Instagram")
                        .param("socialMedia[0].location", "instagram.com/ProgrammerShinobi")
                        .param("socialMedia[1].name", "Facebook")
                        .param("socialMedia[1].location", "facebook.com/ProgrammerShinobi")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success create person Faqih Pratama Muhti " +
                        "with email faqih@example.com and phone 0123456789 " +
                        "with address Jalan Seroja Raya, Bogor, Indonesia, 123456"))
        );
    }

}