package demo.test.controller;

import demo.test.model.entity.ProfileEntity;
import demo.test.service.business.signin.SignInService;
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
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = jwtService.generateToken(authentication);
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        ProfileEntity currentUser = userService.findByUsername(user.getEmail()).get();
//
//        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getFullName(), jwtService.getExpirationDateFromToken(jwt)));
        return signinService.handleSignIn(user);
    }
}
