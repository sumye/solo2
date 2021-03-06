package com.sparta.solo2.controller;

import com.sparta.solo2.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if(userDetails == null) {
            return "index";
        }
        model.addAttribute("username", userDetails.getUsername());
        return "index";
    }

    @GetMapping("/write.html")
    public String write(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username2", userDetails.getUsername());
        return "write";
    }

    @GetMapping("/detail.html")
    public String detail(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if(userDetails == null) {

            return "detail";
        }
        model.addAttribute("username3", userDetails.getUsername());
        return "detail";
    }


}