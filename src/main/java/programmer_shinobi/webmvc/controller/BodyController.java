package programmer_shinobi.webmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import programmer_shinobi.webmvc.model.HelloRequest;
import programmer_shinobi.webmvc.model.HelloResponse;
import tools.jackson.databind.ObjectMapper;

@Controller
public class BodyController {

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(
            path = "/body/hello",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String body(@RequestBody String requestBody) throws Exception {
        HelloRequest helloRequest = objectMapper.readValue(requestBody, HelloRequest.class);

        HelloResponse response = new HelloResponse();
        response.setHello("Hello " + helloRequest.getName());

        return objectMapper.writeValueAsString(response);
    }

}
