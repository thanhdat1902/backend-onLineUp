package com.server.onlineup.controller;

import com.server.onlineup.model.request.ForgetPasswordConfirmRequest;
import com.server.onlineup.model.request.ForgetPasswordEmailRequest;
import com.server.onlineup.model.request.UpdatePasswordRequest;
import com.server.onlineup.service.business.changepassword.ChangePasswordService;
import com.server.onlineup.service.database.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/forget-password")
public class ForgetPasswordController {

    @Autowired
    ChangePasswordService changePasswordService;
    @Autowired
    ProfileService profileService;

    @PostMapping("/confirm-email")
    @ResponseBody
    public ResponseEntity postForgetPasswordEmail(@RequestBody ForgetPasswordEmailRequest request) {
        return changePasswordService.handleEmailForgetPassword(request.email);
    }

    @PostMapping("/verify-otp")
    @ResponseBody
    public ResponseEntity checkOTPForgetPassword(@RequestBody ForgetPasswordConfirmRequest request) {
        return changePasswordService.handleVerifyOTPForgetPassword(request.email, request.otp);
    }

    @PostMapping("/new-password")
    @ResponseBody
    public ResponseEntity updateNewPassword(@RequestBody UpdatePasswordRequest request) {
        return changePasswordService.updatePasswordForget(request.email, request.newPass, request.confirmNewPass);
    }
}
