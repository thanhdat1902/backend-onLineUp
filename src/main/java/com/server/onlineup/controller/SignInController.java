package com.server.onlineup.controller;

import com.server.onlineup.model.entity.ProfileEntity;
import com.server.onlineup.service.business.signin.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin
public class SignInController {

    @Autowired
    private SignInService signinService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ProfileEntity user) {
        return signinService.handleSignIn(user);
    }
}
