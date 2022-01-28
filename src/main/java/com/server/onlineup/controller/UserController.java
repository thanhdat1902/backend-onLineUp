package com.server.onlineup.controller;

import com.server.onlineup.model.request.UpdateFcmtokenRequest;
import com.server.onlineup.service.database.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    ProfileService profileService;

    @PostMapping("/update-fcm-token")
    @ResponseBody
    public ResponseEntity updateFcmToken(@RequestBody UpdateFcmtokenRequest request) {
        return profileService.handleUpdateFcmtoken(request.fcmToken);
    }
}
