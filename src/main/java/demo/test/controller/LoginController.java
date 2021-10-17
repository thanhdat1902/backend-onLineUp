package demo.test.controller;

import demo.test.model.RequestLogin;
import demo.test.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/signup")
public class LoginController {

    LoginService loginService;

    @PostMapping(value = "/getEmail")
    public @ResponseBody
    RequestLogin getEmail(@RequestBody RequestLogin requestLogin) {
        System.out.println(requestLogin.getEmail());
        return requestLogin;
    }
}
