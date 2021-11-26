package com.server.onlineup.model.response;

import com.server.onlineup.model.entity.ProfileEntity;

public class LoginResponse {
    ProfileEntity userInfo;
    JwtResponse jwtResponse;

    public LoginResponse(ProfileEntity userInfo, JwtResponse jwtResponse) {
        this.userInfo = userInfo;
        this.jwtResponse = jwtResponse;
    }
}
