package uz.devior.mohirdevoauth2smtp.usersmtp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserSmtpService {

    private final JavaMailSender javaMailSender;

    @Value("spring.mail.username")
    private String emailFrom;

    public String sendMsg(AbstractAuthenticationToken principal){

        Map<String, Object> map;
        map = ((OAuth2AuthenticationToken) principal).getPrincipal().getAttributes();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailFrom);
        message.setTo((String) map.get("email"));
        message.setSubject("You entered to new app");
        message.setText(String.format("Dear %s. From your email: %s entered to new app", map.get("name"), map.get("email")));

        javaMailSender.send(message);

        return "Message was successfully sent to "+map.get("email");
    }
}
