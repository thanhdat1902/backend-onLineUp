package demo.test.service.business.signin;

import demo.test.common.constant.AuthenticationEnum;
import demo.test.common.exception.APIException;
import demo.test.common.response.BaseResponse;
import demo.test.model.entity.ProfileEntity;
import demo.test.model.response.JwtResponse;
import demo.test.service.implementation.IUserService;
import demo.test.service.provider.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if (true)
            throw new APIException(
                    BaseResponse.<Authentication>Builder()
                            .addMessage(AuthenticationEnum.USERNAME_ERROR)
                            .addData(authentication)
                            .addErrorStatus(HttpStatus.BAD_REQUEST)
            );

        String jwt = jwtService.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ProfileEntity currentUser = userService.findByUsername(user.getEmail()).get();
        JwtResponse jwtResponse = new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getFullName(), jwtService.getExpirationDateFromToken(jwt));
        return ResponseEntity.ok(
                BaseResponse.Builder()
                        .addMessage(AuthenticationEnum.LOGIN_SUCCESS)
                        .addData(jwtResponse)
                        .build()
        );
    }


}
