package demo.test.service;

import demo.test.repository.JavaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JavaService {

    @Autowired
    JavaRepository javaRepository;

    public long getCount() {
        return javaRepository.count();
    }
}