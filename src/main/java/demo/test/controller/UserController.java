package demo.test.controller;

import demo.test.model.request.LoginBody;
import demo.test.model.response.LoginResponse;
import demo.test.repository.UserRepository;
import demo.test.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtService;

    @CrossOrigin(origins = "*")
    @PostMapping ("/login")
    public LoginResponse login(@RequestBody LoginBody body) {
        String token = jwtService.generateToken(body);
        return new LoginResponse(token, "60");
    }
}
