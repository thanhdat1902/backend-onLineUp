package demo.test.controller;

import demo.test.model.request.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @PostMapping(value = "/#")
    public @ResponseBody
    boolean postUsernamePassword(@RequestBody LoginRequest loginRequest) {
        return true;
    }
}
