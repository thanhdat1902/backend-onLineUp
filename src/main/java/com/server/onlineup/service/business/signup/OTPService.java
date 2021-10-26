package com.server.onlineup.service.business.signup;

import com.server.onlineup.common.constant.AuthenticationEnum;
import com.server.onlineup.common.exception.APIException;
import com.server.onlineup.common.response.BaseResponse;
import com.server.onlineup.common.utils.NumberUtils;
import com.server.onlineup.common.utils.TimeUtils;
import com.server.onlineup.model.entity.OTPEntity;
import com.server.onlineup.repository.OTPRepository;
import com.server.onlineup.service.provider.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class OTPService {

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private EmailService emailService;

    public void createForMail(String mail) {
        String randomOTP = NumberUtils.generateRandomString(6);
        OTPEntity otpEntity = new OTPEntity(mail, randomOTP, TimeUtils.getCurrentTimestamp());

        //send OTP to mail
        boolean didSendOTP = emailService.sendSimpleEmail(mail, "Test OTP", "Your OTP is " + randomOTP);
        if (!didSendOTP) {
            throw new APIException(BaseResponse.Builder()
                    .addErrorStatus(HttpStatus.BAD_REQUEST)
                    .addMessage(AuthenticationEnum.INVALID_EMAIL)
            );
        }
        //Save to db
        otpRepository.save(otpEntity);
    }


    public void verifyOtpForEmail(String email, String otp) {
        try {
            OTPEntity userOTP = otpRepository.getById(email);
            checkValidOtp(otp, email, userOTP);
            removeForEmail(email);
        } catch (EntityNotFoundException ignored) {
            throw new APIException(BaseResponse.Builder()
                    .addErrorStatus(HttpStatus.BAD_REQUEST)
                    .addMessage(AuthenticationEnum.NOT_FOUND)
            );
        }
    }

    public void removeForEmail(String email) {
        otpRepository.deleteById(email);
    }

    private void decreaseAttempt(OTPEntity otpEntity) {
        otpEntity.decreaseRetryTime();
        otpRepository.save(otpEntity);
    }

    private void checkValidOtp(String otp, String email, OTPEntity otpEntity) {

        if (otpEntity.getTimeRetry() < 0) {
            removeForEmail(email);
            throw new APIException(BaseResponse.Builder()
                    .addErrorStatus(HttpStatus.BAD_REQUEST)
                    .addMessage(AuthenticationEnum.END_OF_TRY)
            );
        }

        if (!otpEntity.getOTP().equals(otp)) {
            decreaseAttempt(otpEntity);
            throw new APIException(BaseResponse.Builder()
                    .addErrorStatus(HttpStatus.BAD_REQUEST)
                    .addMessage(AuthenticationEnum.WRONG)
            );
        }

        long currentTime = TimeUtils.getCurrentTimestamp();
        if (currentTime - otpEntity.getCreateTime() > 60 * 1000 * 60) {     //1 hour
            removeForEmail(email);
            throw new APIException(BaseResponse.Builder()
                    .addErrorStatus(HttpStatus.BAD_REQUEST)
                    .addMessage(AuthenticationEnum.TIME_OUT)
            );
        }
    }

    public int responseVerifyOtpEmail(String email, String otp) {
        if (email != null) {
            OTPEntity otpEntity = otpRepository.getById(email);
            if (otpEntity.getTimeRetry() == 0) {
                return 2;
            } else {
                if (otpEntity.getOTP().equals(otp)) {
                    return 3;
                } else {
                    otpEntity.decreaseRetryTime();
                    return 4;
                }
            }

        }
        return 1;
    }
}
