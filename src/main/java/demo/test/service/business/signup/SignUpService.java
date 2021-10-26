package demo.test.service.business.signup;

import demo.test.common.constant.AuthenticationEnum;
import demo.test.common.exception.APIException;
import demo.test.common.response.BaseResponse;
import demo.test.model.response.EmailVerificationReponse;
import demo.test.service.database.ProfileService;
import demo.test.service.provider.JwtService;
import demo.test.service.provider.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private OTPService otpService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private RestService restService;

    @Autowired
    private JwtService jwtService;

//    public boolean handleInputEmail(String email) {
//        if (Validator.IsEmail(email)) {
//            otpService.createForMail(email);
//            return true;
//        }
//        return false;
//    }

    //ham handle input email
    public ResponseEntity handleInputEmailForOTP(String email) {

        if (profileService.existingEmail(email)) {
            throw new APIException(BaseResponse.Builder()
                    .addErrorStatus(HttpStatus.BAD_REQUEST)
                    .addMessage(AuthenticationEnum.EXISTING_EMAIL)
            );
        } else {
            otpService.createForMail(email);
        }


        return BaseResponse.Builder()
                .addMessage(AuthenticationEnum.SEND_OTP_SUCCESS).build();
    }

    public ResponseEntity handleVerifyOTP(String email, String OTP) {
        otpService.verifyOtpForEmail(email, OTP);
        String token = jwtService.generateTokenFromEmail(email);
        return BaseResponse.Builder()
                .addData(new EmailVerificationReponse(email, token))
                .addMessage(AuthenticationEnum.OTP_SUCCESS)
                .build();
    }

    public ResponseEntity handleCreateAccount(String email, String fullname, String password) {
        profileService.createAccount(email, fullname, password);
        return BaseResponse.Builder()
                .addMessage(AuthenticationEnum.CREATE_ACCOUNT_SUCCESS)
                .build();
    }

    public ResponseEntity handleFacebookToken(String token) {
        EmailVerificationReponse res = restService.requestProfileFromFbToken(token);

        if (res == null || res.email == null || res.email.equals("")) {
            throw new APIException(
                    BaseResponse.Builder()
                            .addMessage(AuthenticationEnum.FACEBOOK_FAIL)
                            .addErrorStatus(HttpStatus.BAD_REQUEST)
            );
        }

        if (profileService.existingEmail(res.email)) {
            throw new APIException(
                    BaseResponse.Builder()
                            .addMessage(AuthenticationEnum.EXISTING_EMAIL)
                            .addErrorStatus(HttpStatus.BAD_REQUEST)
            );
        }

        //TODO: ADD token here
        res.accessToken = jwtService.generateTokenFromEmail(res.email);
        return BaseResponse.<EmailVerificationReponse>Builder()
                .addData(res)
                .addMessage(AuthenticationEnum.FACEBOOK_SUCCESS)
                .build();
    }

}
