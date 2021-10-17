package demo.test.service;

import demo.test.model.RequestLogin;
import demo.test.repository.JavaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    JavaRepository javaRepository;

    public RequestLogin save(RequestLogin request){
        return request;
    }
}
