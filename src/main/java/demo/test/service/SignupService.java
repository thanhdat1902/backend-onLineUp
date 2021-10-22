package demo.test.service;

import demo.test.constant.OTPEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private OTPService otpService;

    @Autowired
    private ProfileService profileService;

    //ham handle input email
    public OTPEnum handleInputEmailForOTP(String email) {
        // no need to validate email string here
        
        //TODO: create Authenticate manager here
        return otpService.createForMail(email);
    }

    private boolean validateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public boolean handleCreateAccount(String username, String email, String password, String confirmPassword) {
        //? username có unique không? Chưa biết
        if (!password.equals(confirmPassword)) return false;
        else {
            profileService.createAccount(username, password, email);
        }
        return true;
    }
}
