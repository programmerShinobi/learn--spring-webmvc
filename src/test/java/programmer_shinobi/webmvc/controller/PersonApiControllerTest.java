package programmer_shinobi.webmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import programmer_shinobi.webmvc.model.CreatePersonRequest;
import programmer_shinobi.webmvc.model.CreateSocialMediaRequest;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPerson() throws Exception {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName("Faqih");
        request.setMiddleName("Pratama");
        request.setLastName("Muhti");
        request.setEmail("faqih@example.com");
        request.setPhone("123456789");
        request.setHobbies(List.of("Coding", "Reading", "Jogging"));
        request.setSocialMedia(new ArrayList<>());
        request.getSocialMedia().add(new CreateSocialMediaRequest("Facebook", "facebook.com/ProgrammerShinobi"));
        request.getSocialMedia().add(new CreateSocialMediaRequest("Twitter", "twitter.com/ProgrammerShinobi"));
        request.getSocialMedia().add(new CreateSocialMediaRequest("Instagram", "instagram.com/ProgrammerShinobi"));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isOk(),
                content().json(jsonRequest)
        );
    }

    @Test
    void createPersonValidationError() throws Exception {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setMiddleName("Pratama");
        request.setLastName("Muhti");
        request.setHobbies(List.of("Coding", "Reading", "Jogging"));
        request.setSocialMedia(new ArrayList<>());
        request.getSocialMedia().add(new CreateSocialMediaRequest("Facebook", "facebook.com/ProgrammerShinobi"));
        request.getSocialMedia().add(new CreateSocialMediaRequest("Twitter", "twitter.com/ProgrammerShinobi"));
        request.getSocialMedia().add(new CreateSocialMediaRequest("Instagram", "instagram.com/ProgrammerShinobi"));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isBadRequest()
        );
    }

}