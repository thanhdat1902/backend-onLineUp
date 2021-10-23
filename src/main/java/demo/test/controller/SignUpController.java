package demo.test.controller;

import demo.test.model.request.InputEmailOtpRequest;
import demo.test.model.request.InputEmailRequest;
import demo.test.model.request.InputFacebookRequest;
import demo.test.model.request.InputInformationRequest;
import demo.test.model.response.BaseResponse;
import demo.test.service.authentication.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/sign-up")
public class SignUpController {

    @Autowired
    SignupService signupService;

    //Receive email --> validate --> send OTP --> response(success or fail)
    @PostMapping(value = "/post-email")
    @ResponseBody
    public BaseResponse<?> postEmail(@RequestBody InputEmailRequest requestLogin) {
        return signupService.handleInputEmailForOTP(requestLogin.email);
    }

    //verify otp
    @PostMapping(path = "/verify-otp")
    @ResponseBody
    public BaseResponse<?> verifyOTP(@RequestBody InputEmailOtpRequest req) {
        return signupService.handleVerifyOTP(req.email, req.otp);
    }

    //use facebook to sign up
    @PostMapping(path = "/use-facebook")
    @ResponseBody
    public BaseResponse useFacebook(@RequestBody InputFacebookRequest facebookRequest) {
        return signupService.handleFacebookToken(facebookRequest.facebookToken);
    }

    //input username, password and confirm password
    @PostMapping(value = "/create-account")
    @ResponseBody
    public BaseResponse postInformation(@RequestBody InputInformationRequest requestInformation) {
        return signupService.handleCreateAccount(requestInformation.username, requestInformation.email,
                requestInformation.password, requestInformation.confirmPassword);
    }
}
