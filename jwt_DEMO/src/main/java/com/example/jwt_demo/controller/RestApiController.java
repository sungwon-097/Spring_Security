package com.example.jwt_demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin // 인증이 없을 때, security filter 에 등록 인증
@RestController
public class RestApiController {

    @GetMapping("/")
    public String home(){
        return "<h1>home</h1>";
    }
}
