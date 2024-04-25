package com.group2.milestone2.api.auth.service;

import com.group2.milestone2.api.auth.dto.SignInRequestDto;
import com.group2.milestone2.api.auth.dto.SignInResponse;
import com.group2.milestone2.api.auth.dto.SignUpRequestDto;
import com.group2.milestone2.domain.session.domain.Session;
import com.group2.milestone2.domain.session.repository.SessionRepository;
import com.group2.milestone2.domain.user.domain.User;
import com.group2.milestone2.domain.user.repository.UserRepository;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    @Transactional
    public void signUp(SignUpRequestDto request){
        if (request.getPassword1().equals(request.getPassword2())){
            throw new RuntimeException("password mismatch");
        }
        if(userRepository.findById(request.getUserId()).isPresent()){
            throw new RuntimeException("user already exists");
        }

        User user = User.create(request.getUserId(), request.getPassword1());
        userRepository.save(user);
    }

    @Transactional
    public SignInResponse signIn(SignInRequestDto request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(RuntimeException::new);
        if(!user.getPassword().equals(request.getPassword())){
            throw new RuntimeException();
        }
        // login success
        String sessionString;
        do {
            sessionString = generateSessionString(user);
        }while (sessionRepository.findById(sessionString).isPresent());
        sessionRepository.save(Session.create(sessionString, user));


        return SignInResponse.builder()
            .session(sessionString)
            .build();
    }

    private String generateSessionString(User user){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            int index = random.nextInt(alphabet.length());  // 알파벳 문자열 길이 내에서 랜덤 인덱스 생성
            char randomChar = alphabet.charAt(index);  // 랜덤 인덱스에 해당하는 문자 가져오기
            sb.append(randomChar);  // 생성된 랜덤 문자를 StringBuilder에 추가
        }

        return sb.toString();  // 최종적으로 생성된 랜덤 문자열 반환
    }
}
