package demo.test.controller;

import demo.test.constant.AuthenticationEnum;
import demo.test.model.request.InputEmailOtpRequest;
import demo.test.model.request.InputEmailRequest;
import demo.test.model.request.InputFacebookRequest;
import demo.test.model.response.BaseResponse;
import demo.test.model.response.FacebookResponse;
import demo.test.service.authentication.OTPService;
import demo.test.service.authentication.SignupService;
import demo.test.service.database.ProfileService;
import demo.test.service.utilities.RestService;
import demo.test.util.TimeUtils;
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

        FacebookResponse res = restService.requestProfileFromFbToken(facebookRequest.facebookToken);

        //TODO: not add data yet.
        return signupService.handleEmailFacebook(res.email);
    }

    //input username, password and confirm password
//    @PostMapping(value = "/create-account")
//    public @ResponseBody
//    String postInformation(@RequestBody InputInformationRequest requestInformation) {
//        if (signupService.handleCreateAccount(requestInformation.email,
//                requestInformation.password, requestInformation.confirmPassword)) {
//            return "Success";
//        }
//        return "Fail";
//    }

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
