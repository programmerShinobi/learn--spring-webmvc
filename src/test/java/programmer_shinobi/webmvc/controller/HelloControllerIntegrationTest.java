package programmer_shinobi.webmvc.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.EntityExchangeResult;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class HelloControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void helloGuest() {
        EntityExchangeResult<String> stringEntityExchangeResult = restTestClient.get()
                .uri("http://localhost:%d/hello".formatted(port))
                .exchange()
                .expectBody(String.class).returnResult();
        assert stringEntityExchangeResult.getResponseBody() != null;
        Assertions.assertEquals("Hello Guest", stringEntityExchangeResult.getResponseBody().trim());
    }

    @Test
    void helloFaqih() {
        EntityExchangeResult<String> stringEntityExchangeResult = restTestClient.get()
                .uri("http://localhost:%d/hello?name=Faqih".formatted(port))
                .exchange()
                .expectBody(String.class).returnResult();
        assert stringEntityExchangeResult.getResponseBody() != null;
        Assertions.assertEquals("Hello Faqih", stringEntityExchangeResult.getResponseBody().trim());
    }

}
