package demo.test.controller;

import demo.test.model.request.InputEmailOtpRequest;
import demo.test.service.OTPService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/otp")
public class OTPController {
    private OTPService otpService;

    private String status;
    private String description;
    private String msg;

    @PostMapping(value = "/checking")
    public @ResponseBody
    void postOtpEmail(@RequestBody InputEmailOtpRequest requestOtp) {
        int response = otpService.responseVerifyOtpEmail(requestOtp.getEmail(), requestOtp.getOtp());
        switch (response) {
            case 1: {
                status = "fail";
                description = "Empty email";
                msg = "There are some problems with your mail!";
                break;
            }
            case 2: {
                status = "fail";
                description = "Run out of time to input OTP";
                msg = "Too many wrong times, please retry with email.";
                break;
            }
            case 3: {
                status = "success";
                description = "Confirm successfully";
                msg = "Your confirmation success!";
                break;
            }
            case 4: {
                status = "fail";
                description = "Wrong OTP";
                msg = "Wrong OTP! Please check your OTP again!";
                break;
            }
        }
    }
}
