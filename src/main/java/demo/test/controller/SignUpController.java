package demo.test.controller;

import demo.test.constant.AuthenticationEnum;
import demo.test.model.request.InputEmailOtpRequest;
import demo.test.model.request.InputEmailRequest;
import demo.test.model.request.InputFacebookRequest;
import demo.test.model.request.InputInformationRequest;
import demo.test.model.response.BaseResponse;
import demo.test.model.response.FacebookResponse;
import demo.test.service.authentication.OTPService;
import demo.test.service.authentication.SignupService;
import demo.test.service.database.ProfileService;
import demo.test.service.utilities.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/sign-up")
public class SignUpController {

    @Autowired
    SignupService signupService;

    @Autowired
    OTPService otpService;

    @Autowired
    RestService restService;

    @Autowired
    ProfileService profileService;

    //Receive email --> validate --> send OTP --> response(success or fail)
    @PostMapping(value = "/post-email")
    @ResponseBody
    public BaseResponse<?> postEmail(@RequestBody InputEmailRequest requestLogin) {
        AuthenticationEnum status = signupService.handleInputEmailForOTP(requestLogin.email);

        return BaseResponse.Builder()
                .addStatus(status == AuthenticationEnum.SEND_OTP_SUCCESS)
                .addCode(status.getDescCode())
                .addDesc(status.getDesc())
                .addDesc(null);
    }

    //verify otp
    @PostMapping(path = "/verify-otp")
    @ResponseBody
    public BaseResponse<?> verifyOTP(@RequestBody InputEmailOtpRequest req) {
        AuthenticationEnum status = otpService.verifyOtpForEmail(req.email, req.otp);

        //TODO return token here
        return BaseResponse.Builder()
                .addStatus(status == AuthenticationEnum.SUCCESS)
                .addCode(status.getDescCode())
                .addDesc(status.getDesc())
                .build();
    }

    //use facebook to sign up
    @PostMapping(path = "/use-facebook")
    @ResponseBody
    public BaseResponse getUserInformation(@RequestBody InputFacebookRequest facebookRequest) {
        AuthenticationEnum status = AuthenticationEnum.FACEBOOK_SUCCESS;
        FacebookResponse res = restService.requestProfileFromFbToken(facebookRequest.facebookToken);

        //TODO need to check existing user
        if (res.email == null || res.email.equals("")) {
            status = AuthenticationEnum.FACEBOOK_FAIL;
        }
        if (profileService.existingEmail(res.email)) {
            status = AuthenticationEnum.EXISTING_EMAIL;
        }
        return BaseResponse.Builder()
                .addStatus(status == AuthenticationEnum.FACEBOOK_SUCCESS)
                .addCode(status.getDescCode())
                .addDesc(status.getDesc())
//                .addData(res)
                .build();
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
