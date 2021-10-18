package demo.test.controller;

import demo.test.model.request.InputEmailRequest;
import demo.test.model.request.InputFacebookRequest;
import demo.test.model.request.InputInformationRequest;
import demo.test.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sign-up")
public class SignUpController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/post-email")
    public @ResponseBody
    InputEmailRequest postEmail(@RequestBody InputEmailRequest requestLogin) {
        loginService.handleInputEmail(requestLogin.getEmail());
        //System.out.println(requestLogin.getEmail());
        return requestLogin;
    }

    @PostMapping(value = "/use-facebook")
    public @ResponseBody
    InputFacebookRequest postFacebook(@RequestBody InputFacebookRequest requestFacebook) {
        return requestFacebook;
    }

    @PostMapping(value = "/information")
    public @ResponseBody
    InputInformationRequest postInformation(@RequestBody InputInformationRequest requestInformation) {
        return requestInformation;
    }
    //Email, email+otp, token-fb, user-password + password-confirm
    //Reponse cho từng thằng
    //Tạo OTPController --> postOTP + response khác nhau: status: fail + success, description: ..., msg: tuy vao respon ma truyen vao khac nhau.
    //Sign-in: email + password
}
