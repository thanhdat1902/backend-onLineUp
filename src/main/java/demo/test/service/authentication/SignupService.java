package demo.test.service.authentication;

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

    public boolean handleCreateAccount(String username, String email, String password, String confirmPassword) {
        //? username có unique không? Chưa biết
        if (!password.equals(confirmPassword)) return false;
        else {
            profileService.createAccount(username, password, email);
        }
        return true;
    }
}
