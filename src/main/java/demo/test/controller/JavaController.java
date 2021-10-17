package demo.test.controller;

import demo.test.model.FacebookResponse;
import demo.test.model.JavaObj;
import demo.test.repository.JavaRepository;
import demo.test.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping(path = "/demo")
public class JavaController {
    @Autowired
    private JavaRepository userRepository;

    @Autowired
    private RestService rest;

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
}



//Promise
