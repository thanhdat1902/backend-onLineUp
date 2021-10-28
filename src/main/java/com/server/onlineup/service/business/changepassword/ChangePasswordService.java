package com.server.onlineup.service.business.changepassword;

import com.server.onlineup.common.constant.AuthenticationEnum;
import com.server.onlineup.common.exception.APIException;
import com.server.onlineup.common.response.BaseResponse;
import com.server.onlineup.model.response.EmailVerificationReponse;
import com.server.onlineup.service.business.signup.OTPService;
import com.server.onlineup.service.database.ProfileService;
import com.server.onlineup.service.provider.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {
    @Autowired
    ProfileService profileService;
    @Autowired
    OTPService otpService;
    @Autowired
    JwtService jwtService;

    public ResponseEntity<?> handleEmailForgetPassword(String email) {
        if (!profileService.existingEmail(email)) {
            throw new APIException(BaseResponse.Builder()
                    .addErrorStatus(HttpStatus.BAD_REQUEST)
                    .addMessage(AuthenticationEnum.NOT_EXISTING_EMAIL_TO_RESET_PASS)
            );
        } else {
            otpService.createForMail(email);
        }

        return BaseResponse.Builder()
                .addMessage(AuthenticationEnum.SEND_OTP_SUCCESS).build();
    }

    public ResponseEntity<?> handleVerifyOTPForgetPassword(String email, String OTP) {
        otpService.verifyOtpForEmail(email, OTP);
        String token = jwtService.generateTokenFromEmail(email);
        return BaseResponse.Builder()
                .addData(new EmailVerificationReponse(email, token))
                .addMessage(AuthenticationEnum.OTP_SUCCESS)
                .build();
    }

    public ResponseEntity<?> updatePasswordForget(String email, String newPass, String confirmNewPass) {
        if (email == null || !profileService.existingEmail(email)) {
            throw new APIException(BaseResponse.Builder()
                    .addErrorStatus(HttpStatus.BAD_REQUEST)
                    .addMessage(AuthenticationEnum.INVALID_EMAIL)
            );
        }
        if (newPass == null || newPass.equals("") || !newPass.equals(confirmNewPass)) {
            throw new APIException(BaseResponse.Builder()
                    .addErrorStatus(HttpStatus.BAD_REQUEST)
                    .addMessage(AuthenticationEnum.WRONG_CONFIRM_PASSWORD)
            );
        }
        profileService.updatePassword(email, newPass);
        return BaseResponse.Builder()
                .addMessage(AuthenticationEnum.CHANGE_PASS_SUCCESS)
                .build();
    }
}
