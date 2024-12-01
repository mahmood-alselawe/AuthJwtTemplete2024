package com.takarub.AuthJwtTemplate.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@Hidden
public class DemoController {


    @GetMapping
    public String helloWorld(){
        return "TEST";
    }
}
