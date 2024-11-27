package com.takarub.AuthJwtTemplate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String Get(){
        return "GET ADMIN:READ&&READ";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public String Post(){
        return "Post ADMIN:READ&&READ";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public String Put(){
        return "Put ADMIN:READ&&READ";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public String Delete(){
        return "Delete ADMIN:READ&&READ";
    }
}
