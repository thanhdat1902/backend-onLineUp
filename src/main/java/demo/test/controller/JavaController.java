package demo.test.controller;

import demo.test.model.JavaObj;
import demo.test.repository.JavaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class JavaController {
    @Autowired
    private JavaRepository userRepository;

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<JavaObj> getAllUsers() {
        System.out.println(userRepository.count());

        JavaObj obj = new JavaObj();
        obj.setName("Test 1");

        userRepository.save(obj);

        return userRepository.findAll();
    }
}
