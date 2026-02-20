package programmer_shinobi.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import programmer_shinobi.webmvc.model.User;

@Controller
public class UserController {

    @GetMapping("/user/current")
    @ResponseBody
    public String getUser(@SessionAttribute(name = "user")User user) {
        return "Hello " + user.getUsername();
    }

}
