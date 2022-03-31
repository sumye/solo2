package com.sparta.solo2.service;

import com.sparta.solo2.dto.SignupRequestDto;
import com.sparta.solo2.model.User;
import com.sparta.solo2.model.UserRoleEnum;
import com.sparta.solo2.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Nested
    @DisplayName("유효성검사 객체 생성")
    class creatusername {
        private String username;
        private String password;
        private String passwordcheck;

        @Test
        @DisplayName("유효성 검사가 제대로 작동하는지")
        void checkregisterUser() {
            //given
            username = "wltnals00";
            password = "asdf";
            passwordcheck = "asdf";


            SignupRequestDto requestDto = new SignupRequestDto(
                    username, password, passwordcheck
            );

            UserService userService = new UserService(userRepository, passwordEncoder);
            assertEquals("success", userService.registerUser(requestDto));

            User user = new User(
                    requestDto.getUsername(),
                    requestDto.getPassword()
            );

            userRepository.save(user);
        }

            @Test
            @DisplayName("잘못된 아이디값이 입력됫을때 유효성검사가 실패하는지")

            void checkidregisterUser () {
                //given
                username = "지수민";
                password = "asdf";
                passwordcheck = "asdf";


                SignupRequestDto requestDto = new SignupRequestDto(
                        username, password, passwordcheck
                );

                UserService userService = new UserService(userRepository, passwordEncoder);
                assertEquals("아이디를 확인해 주세요.", userService.registerUser(requestDto));


            }

        @Test
        @DisplayName("잘못된 비밀번호값이 입력됫을때 유효성검사가 실패하는지")

        void checkpasswordregisterUser () {
            //given
            username = "wltnals00";
            password = "asd";
            passwordcheck = "asd";


            SignupRequestDto requestDto = new SignupRequestDto(
                    username, password, passwordcheck
            );

            UserService userService = new UserService(userRepository, passwordEncoder);
            assertEquals("비밀번호를 확인해 주세요.", userService.registerUser(requestDto));


        }

        @Test
        @DisplayName("비밀번호와 비밀번호확인 값이 다를 때 유효성검사가 실패하는지")

        void checkpassword2registerUser () {
            //given
            username = "wltnals00";
            password = "asd";
            passwordcheck = "123";


            SignupRequestDto requestDto = new SignupRequestDto(
                    username, password, passwordcheck
            );

            UserService userService = new UserService(userRepository, passwordEncoder);
            assertEquals("비밀번호를 확인해 주세요.", userService.registerUser(requestDto));


        }

        @Test
        @DisplayName("비밀번호에 아이디값이 들어갈때 유효성검사 실패하는지")

        void checkpassword3registerUser () {
            //given
            username = "user";
            password = "user1";
            passwordcheck = "user1";


            SignupRequestDto requestDto = new SignupRequestDto(
                    username, password, passwordcheck
            );

            UserService userService = new UserService(userRepository, passwordEncoder);
            assertEquals("비밀번호를 확인해 주세요.", userService.registerUser(requestDto));


        }

        @Test
        @DisplayName("id중복 유효성 검사가 정상 작동하는지")
        void checkduplicationregisterUser() {
            MockUserRepository mockUserRepository = new MockUserRepository();
            //given
            username = "wltnals00";
            password = "asdf";
            passwordcheck = "asdf";


            SignupRequestDto requestDto = new SignupRequestDto(
                    username, password, passwordcheck
            );

            User user = new User(
                    requestDto.getUsername(),
                    requestDto.getPassword()
            );
            mockUserRepository.save(user);

            username = "wltnals00";
            password = "1234";
            passwordcheck = "1234";


             requestDto = new SignupRequestDto(
                    username, password, passwordcheck
            );
            assertTrue(mockUserRepository.existsByUsername(requestDto.getUsername()) );


        }

        }


    }
