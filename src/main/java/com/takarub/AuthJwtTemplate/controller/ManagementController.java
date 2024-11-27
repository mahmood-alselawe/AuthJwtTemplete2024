package com.takarub.AuthJwtTemplate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Management")
@RequiredArgsConstructor
public class ManagementController {

    @GetMapping
    public String Get(){
        return "GET Management:READ&&READ";
    }

    @PostMapping
    public String Post(){
        return "Post Management:READ&&READ";
    }

    @PutMapping
    public String Put(){
        return "Put Management:READ&&READ";
    }

    @DeleteMapping
    public String Delete(){
        return "Delete Management:READ&&READ";
    }
}
