package demo.test.controller;

import demo.test.constant.OTPEnum;
import demo.test.model.request.InputEmailOtpRequest;
import demo.test.model.request.InputEmailRequest;
import demo.test.model.request.InputFacebookRequest;
import demo.test.model.request.InputInformationRequest;
import demo.test.model.response.BaseResponse;
import demo.test.service.OTPService;
import demo.test.service.SignupService;
import demo.test.util.TimeUtils;
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
    SignupService signupService;
    @Autowired
    OTPService otpService;

    //Receive email --> validate --> send OTP --> response(success or fail)
    @PostMapping(value = "/post-email")
    public @ResponseBody
    String postEmail(@RequestBody InputEmailRequest requestLogin) {
        if (signupService.handleInputEmail(requestLogin.getEmail())) {
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

    @PostMapping(value = "/use-facebook")
    public @ResponseBody
    InputFacebookRequest postFacebook(@RequestBody InputFacebookRequest requestFacebook) {
        return requestFacebook;
    }


    //Email, email+otp, token-fb, user-password + password-confirm
    //Reponse cho từng thằng
    //Tạo OTPController --> postOTP + response khác nhau: status: fail + success, description: ..., msg: tuy vao respon ma truyen vao khac nhau.
    //Sign-in: email + password
}
