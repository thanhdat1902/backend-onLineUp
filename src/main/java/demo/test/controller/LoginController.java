package demo.test.controller;

import demo.test.model.request.LoginRequest;
import demo.test.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/#")
    public @ResponseBody
    String postUsernamePassword(@RequestBody LoginRequest loginRequest) {
        if (loginService.handleLogin(loginRequest.username, loginRequest.password))
            return "Success";
        return "Fail";
    }


}
