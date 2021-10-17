package demo.test.controller;

import demo.test.model.request.InputEmailRequest;
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

    @PostMapping(value = "/get-email")
    public @ResponseBody
    InputEmailRequest postEmail(@RequestBody InputEmailRequest requestLogin) {
//        System.out.println(requestLogin.getEmail());
        return requestLogin;
    }

    //Email, email+otp, token-fb, user-password, password-confirm
    //Reponse cho từng thằng
    //Tạo OTPController --> postOTP + response khác nhau: status: fail + success, description: ..., msg: tuy vao respon ma truyen vao khac nhau.
}
