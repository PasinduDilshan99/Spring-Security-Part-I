package com.spring.security.Spring.Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1")
public class HelloController {

    @GetMapping(path = "/test")
    public String helloWorld(){
        return "Hello World";
    }
}
