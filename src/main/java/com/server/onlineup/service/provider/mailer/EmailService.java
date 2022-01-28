package com.server.onlineup.service.provider.mailer;

import com.server.onlineup.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ProfileRepository profileRepository;

    public boolean sendSimpleEmail(String target, String subject, String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(target);
        msg.setSubject(subject);
        msg.setText(content);
        try {
            javaMailSender.send(msg);
            return true;
        } catch (MailSendException e) {
            return false;
        }
    }

    public CompletableFuture<Boolean> sendSimpleEmailAsync(String target, String subject, String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(target);
        msg.setSubject(subject);
        msg.setText(content);
        try {
//            return CompletableFuture.supplyAsync(new Supplier<Boolean>() {
//                @Override
//                public Boolean get() {
//                    javaMailSender.send(msg);
//                    return true;
//                }
//            });


            return CompletableFuture.supplyAsync(() -> {
                javaMailSender.send(msg);
                return true;
            });

        } catch (MailSendException e) {
            return CompletableFuture.completedFuture(false);
        }
    }
}
