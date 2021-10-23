package demo.test.controller;

import demo.test.model.entity.JavaObj;
import demo.test.model.response.FacebookResponse;
import demo.test.repository.JavaRepository;
import demo.test.service.EmailService;
import demo.test.service.OTPService;
import demo.test.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@CrossOrigin
@RequestMapping(path = "/demo")
public class JavaController {
    @Autowired
    private JavaRepository userRepository;

    @Autowired
    private RestService rest;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OTPService otpService;

    @GetMapping(path = "/create-otp")
    public String createOTP() {

        return "";
    }

    @GetMapping(path = "/test-fb")
    public @ResponseBody
    FacebookResponse getUser() {

        URI uri = UriComponentsBuilder
                .fromUriString("https://graph.facebook.com/me")
                .queryParam("fields", "email")
                .queryParam("access_token", "GGQVlaMU5DYXg0M2tyTVJhZAXlBTTNpMDBTWktLVzM1T2VyZA3VnNk5ObkhXbzJOYWF2OHJQRk05dG1NSnpUTW9fVV94N2RGR0s5clBMRzVZAclVKR3UxbHpXb1VPMlByWTVKTHdqbHJjWkpnWUpJVWtiZAElNUVdHdXl5NTN0NUdPX0hGWjVnTU50eS1UckR3ck1SY3J3b3V0TTdLRmZAUbFEZD")
                .build()
                .toUri();


        FacebookResponse res = rest.restTemplate.getForObject(uri, FacebookResponse.class);

//        emailService.sendSimpleEmail("ntlam19@apcs.vn", "Subject", "content");

//        otpService.createForMail("lamnguyem5464@gmail.com");
        return res;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<JavaObj> getAllUsers() {
        JavaObj obj = new JavaObj();
        obj.setName("Test 1");

        userRepository.save(obj);

        return userRepository.findAll();
    }

//    @PostMapping(path = "/create-opt")
//    @ResponseBody
//    public TestResponse createOTP(@RequestBody TestRequest req) {
//        TestResponse res = new TestResponse();
//        res.params1 =
//                otpService.createForMail(req.params1).getDesc();
//        return res;
//    }
//
//    @PostMapping(path = "/verify-opt")
//    @ResponseBody
//    public TestResponse verifyOTP(@RequestBody TestRequest req) {
//        TestResponse res = new TestResponse();
//        res.params1 = otpService.verifyOtpForEmail(req.params1, req.params2).getDesc();
//        return res;
//    }
}