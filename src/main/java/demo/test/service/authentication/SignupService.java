package demo.test.service.authentication;

import demo.test.constant.AuthenticationEnum;
import demo.test.model.response.BaseResponse;
import demo.test.service.database.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private OTPService otpService;

    @Autowired
    private ProfileService profileService;

    //ham handle input email
    public AuthenticationEnum handleInputEmailForOTP(String email) {
        if (profileService.existingEmail(email)) {
            return AuthenticationEnum.EXISTING_EMAIL;
        }
        return otpService.createForMail(email);
    }

    public boolean handleCreateAccount(String username, String email, String password, String confirmPassword) {
        //TODO: check username exists or not.
        if (!password.equals(confirmPassword)) return false;
        else {
            profileService.createAccount(username, password, email);
        }
        return true;
    }

    public BaseResponse handleEmailFacebook(String email) {
        AuthenticationEnum status = AuthenticationEnum.FACEBOOK_SUCCESS;
        if (email == null || email.equals("")) {
            status = AuthenticationEnum.FACEBOOK_FAIL;
        }
        if (profileService.existingEmail(email)) {
            status = AuthenticationEnum.EXISTING_EMAIL;
        }
        return BaseResponse.Builder()
                .addStatus(status == AuthenticationEnum.FACEBOOK_SUCCESS)
                .addCode(status.getDescCode())
                .addDesc(status.getDesc())
//                .addData(res)
                .build();
    }
}
