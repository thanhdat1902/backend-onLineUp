package com.server.onlineup.service.business.signin;

import com.server.onlineup.common.constant.AuthenticationEnum;
import com.server.onlineup.common.response.BaseResponse;
import com.server.onlineup.model.entity.ProfileEntity;
import com.server.onlineup.model.response.JwtResponse;
import com.server.onlineup.service.provider.JwtService;
import com.server.onlineup.service.implementation.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SignInService {
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity handleSignIn(ProfileEntity user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ProfileEntity currentUser = userService.findByUsername(user.getEmail()).get();
        JwtResponse jwtResponse = new JwtResponse(jwt);
        return BaseResponse.Builder()
                .addMessage(AuthenticationEnum.LOGIN_SUCCESS)
                .addData(jwtResponse)
                .build();
    }


}
