package demo.test.service;

import demo.test.constant.OTPEnum;
import demo.test.model.entity.OTPEntity;
import demo.test.repository.OTPRepository;
import demo.test.util.NumberUtils;
import demo.test.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class OTPService {

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private EmailService emailService;

    public OTPEnum createForMail(String mail) {
        String randomOTP = NumberUtils.generateRandomString(6);
        OTPEntity otpEntity = new OTPEntity(mail, randomOTP, TimeUtils.getCurrentTimestamp());

        //send OTP to mail
        boolean didSendOTP = emailService.sendSimpleEmail(mail, "Test OTP", "Your OTP is " + randomOTP);
        if (!didSendOTP) {
            return OTPEnum.INVALID_EMAIL;
        }

        //Save to db
        otpRepository.save(otpEntity);
        return OTPEnum.SEND_OTP_SUCCESS;
    }


    public OTPEnum verifyOtpForEmail(String email, String otp) {
        try {
            OTPEntity userOTP = otpRepository.getById(email);
            OTPEnum status = checkValidOtp(otp, userOTP);

            switch (status) {
                case SUCCESS:
                case END_OF_TRY:
                case TIME_OUT:
                    removeForEmail(email);
                    break;
                case WRONG:
                    decreaseAttempt(userOTP);
                    break;
                default:
            }

            return status;
        } catch (EntityNotFoundException ignored) {
            return OTPEnum.NOT_FOUND;
        }
    }

    public void removeForEmail(String email) {
        otpRepository.deleteById(email);
    }

    private void decreaseAttempt(OTPEntity otpEntity) {
        otpEntity.decreaseRetryTime();
        otpRepository.save(otpEntity);
    }

    private OTPEnum checkValidOtp(String otp, OTPEntity otpEntity) {

        if (otpEntity.getTimeRetry() < 0) {
            return OTPEnum.END_OF_TRY;
        }

        if (!otpEntity.getOTP().equals(otp)) {
            return OTPEnum.WRONG;
        }

        long currentTime = TimeUtils.getCurrentTimestamp();
        if (currentTime - otpEntity.getCreateTime() > 60 * 1000 * 60) {     //1 hour
            return OTPEnum.TIME_OUT;
        }

        return OTPEnum.SUCCESS;
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
