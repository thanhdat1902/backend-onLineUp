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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@CrossOrigin
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
    @ResponseBody
    public BaseResponse<?> postEmail(@RequestBody InputEmailRequest requestLogin) {
        //TODO: need to check existing users
        OTPEnum status = signupService.handleInputEmailForOTP(requestLogin.email);

        return BaseResponse.Builder()
                .addStatus(status == OTPEnum.SEND_OTP_SUCCESS)
                .addCode(status.getDescCode())
                .addDesc(status.getDesc())
                .addDesc(null);

    }

    //verify otp
    @PostMapping(path = "/verify-otp")
    @ResponseBody
    public BaseResponse<?> verifyOTP(@RequestBody InputEmailOtpRequest req) {
        OTPEnum status = otpService.verifyOtpForEmail(req.email, req.otp);

        //TODO return token here
        return BaseResponse.Builder()
                .addStatus(status == OTPEnum.SUCCESS)
                .addCode(status.getDescCode())
                .addDesc(status.getDesc())
                .build();
    }

    //use facebook to sign up
    @PostMapping(path = "/use-facebook")
    @ResponseBody
    public BaseResponse getUserInformation(@RequestBody InputFacebookRequest facebookRequest) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://graph.facebook.com/me")
                .queryParam("fields", "email")
                .queryParam("access_token", facebookRequest.facebookToken)
                .build()
                .toUri();

        FacebookResponse res = rest.restTemplate.getForObject(uri, FacebookResponse.class);
        //TODO need to check existing user
        return new BaseResponse<FacebookResponse>()
                .addData(res);
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
