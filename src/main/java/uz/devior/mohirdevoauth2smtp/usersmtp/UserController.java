package uz.devior.mohirdevoauth2smtp.usersmtp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserSmtpService userSmtpService;

    @GetMapping
    public ResponseEntity<String> getUser(Principal principal){
        String s = userSmtpService.sendMsg((AbstractAuthenticationToken) principal);
        return ResponseEntity.ok(s);
    }
}
