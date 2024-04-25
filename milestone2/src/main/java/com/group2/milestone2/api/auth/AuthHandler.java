package com.group2.milestone2.api.auth;

import com.group2.milestone2.domain.user.domain.User;
import com.group2.milestone2.domain.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthHandler {

    UserRepository userRepository;
    public void signUp(SignUpRequestDto request){
        if (request.getPassword1().equals(request.getPassword2())){
            throw new RuntimeException();
        }
        User user = User.create(request.getUserId(), request.getPassword1());
    }
}
