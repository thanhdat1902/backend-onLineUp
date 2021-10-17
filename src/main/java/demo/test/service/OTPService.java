package demo.test.service;

import demo.test.model.entity.OTPEntity;
import demo.test.repository.OTPRepository;
import demo.test.util.NumberUtils;
import demo.test.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPService {

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private EmailService emailService;

    public void createForMail(String mail) {
        String randomOTP = NumberUtils.generateRandomString(6);
        OTPEntity otpEntity = new OTPEntity(mail, randomOTP, TimeUtils.getCurrentTimestamp());
        otpRepository.save(otpEntity);
        emailService.sendSimpleEmail("ntlam19@apcs.vn", "Test OTP", "Your OTP is " + randomOTP);
    }

    public void removeForEmail(String email) {
        otpRepository.deleteById(email);
    }

    public boolean verifyOtpForEmail(String email, String otp) {
        OTPEntity otpEntity = otpRepository.getById(email);
        if (email != null) {
            if (otpEntity.getOTP().equals(otp)) {
                otpRepository.deleteById(email);
                return true;
            } else {
                otpEntity.decreaseRetryTime();
                otpRepository.save(otpEntity);
            }
        }
        return false;
    }
}
