package demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private OTPService otpService;

    //ham handle input email
    public void handleInputEmail(String email) {
        if (validateEmail(email)) {
            otpService.createForMail(email);

        }
    }

    private boolean validateEmail(String email) {
        return true;
    }
}
