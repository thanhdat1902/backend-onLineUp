package demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private OTPService otpService;

    //ham handle input email
    public boolean handleInputEmail(String email) {
        if (validateEmail(email)) {
            otpService.createForMail(email);
            return true;
        }
        return false;
    }

    private boolean validateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public boolean handleCreateAccount(String username, String password, String confirmPassword) {
        if (password != confirmPassword) return false;
        else {

        }
        return true;
    }
}
