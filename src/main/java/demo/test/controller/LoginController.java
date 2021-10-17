package demo.test.controller;

import demo.test.model.JavaObj;
import demo.test.model.RequestLogin;
import demo.test.repository.JavaRepository;
import demo.test.service.LoginService;
import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping("/signup")
public class LoginController {

    LoginService loginService;

    @PostMapping(value = "/getEmail")
    public @ResponseBody RequestLogin getEmail(@RequestBody RequestLogin requestLogin) {
        System.out.println(requestLogin.getEmail());
        return requestLogin;
    }
}
