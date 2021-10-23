package demo.test.service.authentication;

import demo.test.constant.AuthenticationEnum;
import demo.test.model.response.BaseResponse;
import demo.test.model.response.FacebookResponse;
import demo.test.service.database.ProfileService;
import demo.test.service.utilities.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private OTPService otpService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private RestService restService;


    //ham handle input email
    public BaseResponse handleInputEmailForOTP(String email) {
        AuthenticationEnum status;

        if (profileService.existingEmail(email)) {
            status = AuthenticationEnum.EXISTING_EMAIL;
        } else {
            status = otpService.createForMail(email);
        }

        return BaseResponse.Builder()
                .addStatus(status == AuthenticationEnum.SEND_OTP_SUCCESS)
                .addCode(status.getDescCode())
                .addDesc(status.getDesc())
                .addDesc(null);
    }

    public BaseResponse handleVerifyOTP(String email, String OTP) {
        AuthenticationEnum status = otpService.verifyOtpForEmail(email, OTP);

        //TODO return token here
        return BaseResponse.Builder()
                .addStatus(status == AuthenticationEnum.SUCCESS)
                .addCode(status.getDescCode())
                .addDesc(status.getDesc())
                .build();
    }

    public BaseResponse handleCreateAccount(String username, String email, String password, String confirmPassword) {
        AuthenticationEnum status;
        if (username == null || profileService.existingUsername(username)) {
            status = AuthenticationEnum.USERNAME_ERROR;
        } else if (!password.equals(confirmPassword)) {
            status = AuthenticationEnum.CONFIRM_PASSWORD_FAIL;
        } else {
            profileService.createAccount(username, password, email);
            status = AuthenticationEnum.CREATE_ACCOUNT_SUCCESS;
        }
        return BaseResponse.Builder()
                .addStatus(status == AuthenticationEnum.CREATE_ACCOUNT_SUCCESS)
                .addCode(status.getDescCode())
                .addDesc(status.getDesc())
                .build();
    }

    public BaseResponse handleFacebookToken(String token) {
        FacebookResponse res = null;
        AuthenticationEnum status = AuthenticationEnum.FACEBOOK_SUCCESS;
        res = restService.requestProfileFromFbToken(token);
        if (res == null || res.email == null || res.email.equals("")) {
            status = AuthenticationEnum.FACEBOOK_FAIL;
        }
        if (profileService.existingEmail(res.email)) {
            status = AuthenticationEnum.EXISTING_EMAIL;
        }

        //TODO: ADD token here
        return BaseResponse.<FacebookResponse>Builder()
                .addData(res)
                .addStatus(status == AuthenticationEnum.FACEBOOK_SUCCESS)
                .addDesc(status.getDesc())
                .addCode(status.getDescCode());
    }

}
