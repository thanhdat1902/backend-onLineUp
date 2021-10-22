package demo.test.controller;

import demo.test.constant.OTPEnum;
import demo.test.model.request.InputEmailOtpRequest;
import demo.test.model.request.InputEmailRequest;
import demo.test.model.request.InputFacebookRequest;
import demo.test.model.request.InputInformationRequest;
import demo.test.model.response.BaseResponse;
import demo.test.model.response.FacebookResponse;
import demo.test.service.authentication.OTPService;
import demo.test.service.authentication.SignupService;
import demo.test.service.utilities.EmailService;
import demo.test.service.utilities.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/sign-up")
public class SignUpController {

    @Autowired
    SignupService signupService;
    @Autowired
    OTPService otpService;
    @Autowired
    RestService rest;
    @Autowired
    EmailService emailService;

    //Receive email --> validate --> send OTP --> response(success or fail)
    @PostMapping(value = "/post-email")
    @ResponseBody
    public BaseResponse<?> postEmail(@RequestBody InputEmailRequest requestLogin) {
        //TODO: need to check existing users
        //Done / not test yet
        if (emailService.existingEmail(requestLogin.email)) {
            OTPEnum error = OTPEnum.INVALID_EMAIL;
            return new BaseResponse<>(error.getDescCode(), error.getDesc(), null);
        }
        OTPEnum status = signupService.handleInputEmailForOTP(requestLogin.email);
        return new BaseResponse<>(status.getDescCode(), status.getDesc(), null);
    }

    //verify otp
    @PostMapping(path = "/verify-otp")
    @ResponseBody
    public BaseResponse<?> verifyOTP(@RequestBody InputEmailOtpRequest req) {
        OTPEnum status = otpService.verifyOtpForEmail(req.email, req.otp);

        //TODO return token here
        return new BaseResponse<>(status.getDescCode(), status.getDesc(), null);
    }

    //use facebook to sign up
    @PostMapping(path = "/use-facebook")
    @ResponseBody
    public BaseResponse<FacebookResponse> getUserInformation(@RequestBody InputFacebookRequest facebookRequest) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://graph.facebook.com/me")
                .queryParam("fields", "email")
                .queryParam("access_token", facebookRequest.facebookToken)
                .build()
                .toUri();

        FacebookResponse res = rest.restTemplate.getForObject(uri, FacebookResponse.class);
        //TODO need to check existing user
        //done / not test yet
        if (emailService.existingEmail(res.email)) {
            return new BaseResponse<FacebookResponse>("fail", "Already exist email", null);
        }
        return new BaseResponse<FacebookResponse>(res);
    }

    //input username, password and confirm password
    @PostMapping(value = "/create-account")
    public @ResponseBody
    String postInformation(@RequestBody InputInformationRequest requestInformation) {
        if (signupService.handleCreateAccount(requestInformation.username, requestInformation.email,
                requestInformation.password, requestInformation.confirmPassword)) {
            return "Success";
        }
        return "Fail";
    }
}
