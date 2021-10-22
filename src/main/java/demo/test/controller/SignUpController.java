package demo.test.controller;

import demo.test.constant.OTPEnum;
import demo.test.model.request.InputEmailOtpRequest;
import demo.test.model.request.InputEmailRequest;
import demo.test.model.request.InputFacebookRequest;
import demo.test.model.request.InputInformationRequest;
import demo.test.model.response.BaseResponse;
import demo.test.model.response.FacebookResponse;
import demo.test.service.OTPService;
import demo.test.service.RestService;
import demo.test.service.SignupService;
import demo.test.util.TimeUtils;
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

    //Receive email --> validate --> send OTP --> response(success or fail)
    @PostMapping(value = "/post-email")
    public @ResponseBody
    String postEmail(@RequestBody InputEmailRequest requestLogin) {
        if (signupService.handleInputEmail(requestLogin.email)) {
            return "Success";
        }
        return "Wrong format email";
    }

    //verify otp
    @PostMapping(path = "/verify-otp")
    public @ResponseBody
    OTPEnum verifyOTP(@RequestBody InputEmailOtpRequest req) {
        return otpService.verifyOtpForEmail(req.email, req.otp);
    }

    //use facebook to sign up
    @PostMapping(path = "/use-facebook")
    public @ResponseBody
    FacebookResponse getUserInformation(@RequestBody InputFacebookRequest facebookRequest) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://graph.facebook.com/me")
                .queryParam("fields", "email")
                .queryParam("access_token", facebookRequest.facebookToken)
                .build()
                .toUri();

        FacebookResponse res = rest.restTemplate.getForObject(uri, FacebookResponse.class);
        return res;
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

    @PostMapping(value = "/test-base-response")
    public @ResponseBody
    BaseResponse<InputEmailOtpRequest> testTingGson(@RequestBody InputEmailOtpRequest request) {
        BaseResponse<InputEmailOtpRequest> a = new BaseResponse<InputEmailOtpRequest>(request, TimeUtils.getCurrentTimestamp());
        return a;
    }
}
