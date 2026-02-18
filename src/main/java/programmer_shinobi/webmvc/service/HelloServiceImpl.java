package programmer_shinobi.webmvc.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return name == null ? "Hello Guest" : "Hello " + name;
    }
}
