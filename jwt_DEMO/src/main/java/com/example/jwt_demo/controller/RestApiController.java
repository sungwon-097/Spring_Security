package com.example.jwt_demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin // 인증이 없을 때, security filter 에 등록 인증
@RestController
public class RestApiController {

    @GetMapping("/")
    public String landing(){
        return "starting page";
    }
    @GetMapping("/home")
    public String home(){
        return "<h1>home</h1>";
    }

    @PostMapping("/token")
    public String token(){
        return "<h1>token</h1>";
    }
}
