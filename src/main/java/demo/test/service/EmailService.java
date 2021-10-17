package demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleEmail(String target, String subject, String content) {
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(target);
        msg.setSubject(subject);
        msg.setText(content);

        javaMailSender.send(msg);

    }

}
