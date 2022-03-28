package com.sparta.solo2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.solo2.dto.SignupRequestDto;
import com.sparta.solo2.service.KakaoUserService;
import com.sparta.solo2.service.UserService;
import com.sparta.solo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto, RedirectAttributes rttr) {
        String str = userService.registerUser(requestDto);
        if ( str.equals("success") ) {
            return "redirect:/user/login";
        }
        rttr.addFlashAttribute("response", str);
        System.out.println(str);
        return "redirect:/user/signup";
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}