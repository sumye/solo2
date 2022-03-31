package com.sparta.solo2.service;

import com.sparta.solo2.dto.SignupRequestDto;
import com.sparta.solo2.model.User;
import com.sparta.solo2.model.UserRoleEnum;
import com.sparta.solo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
    private String pattern = "^[a-zA-Z0-9]*$";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordcheck = requestDto.getPasswordcheck();

        // 아이디 유효성 검사
        if ( !Pattern.matches(pattern, username) || username.length() < 3 ) {
            return "아이디를 확인해 주세요.";
        }
        // 비밀번호 유효성 검사
        if ( ( password.contains(username)|| password.length() < 4 ) || !password.equals(passwordcheck) ) {
            return "비밀번호를 확인해 주세요.";
        }
// 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            return "아이디가 중복됩니다.";
        }

// 패스워드 암호화
        password = passwordEncoder.encode(requestDto.getPassword());
//        String email = requestDto.getEmail();

// 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, passwordcheck, role);
        userRepository.save(user);
        return "success";
    }
}