package demo.test.service;

import demo.test.util.NumberUtils;
import demo.test.util.TimeUtils;
import demo.test.model.entity.OTPEntity;
import demo.test.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPService {

    @Autowired
    private OTPRepository otpRepository;

    public void createForMail(String mail) {
        OTPEntity otpEntity = new OTPEntity(mail, NumberUtils.generateRandomString(5), TimeUtils.getCurrentTimestamp());
        otpRepository.save(otpEntity);
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
