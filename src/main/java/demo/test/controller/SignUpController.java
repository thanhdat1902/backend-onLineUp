package demo.test.controller;

import demo.test.model.request.InputEmailOtpRequest;
import demo.test.model.request.InputEmailRequest;
import demo.test.model.request.InputFacebookRequest;
import demo.test.model.request.InputInformationRequest;
import demo.test.service.business.signup.OTPService;
import demo.test.service.business.signup.SignUpService;
import demo.test.service.provider.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/sign-up")
public class SignUpController {
    @Autowired
    SignUpService signupService;

    @Autowired
    OTPService otpService;

    @Autowired
    RestService restService;


    //Receive email --> validate --> send OTP --> response(success or fail)
    @PostMapping(value = "/post-email")
    @ResponseBody
    public ResponseEntity<?> postEmail(@RequestBody InputEmailRequest requestLogin) {
        return signupService.handleInputEmailForOTP(requestLogin.email);
    }

    //verify otp
    @PostMapping(path = "/verify-otp")
    @ResponseBody
    public ResponseEntity<?> verifyOTP(@RequestBody InputEmailOtpRequest req) {
        return signupService.handleVerifyOTP(req.email, req.otp);
    }


    //use facebook to sign up
    @PostMapping(path = "/use-facebook")
    @ResponseBody
    public ResponseEntity useFacebook(@RequestBody InputFacebookRequest facebookRequest) {
        return signupService.handleFacebookToken(facebookRequest.facebookToken);
    }

    //input fullname, password and confirm password
    @PostMapping(value = "/create-account")
    @ResponseBody
    public ResponseEntity postInformation(@RequestBody InputInformationRequest requestInformation) {
        return signupService.handleCreateAccount(requestInformation.email, requestInformation.fullname,
                requestInformation.password);
    }


}
