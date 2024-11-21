package com.spring.security.test.SpringSecurityTest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class Home {
    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/normal")
    public ResponseEntity<String> normaluser(){
        return ResponseEntity.ok("i am normal user");

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> adminuser(){
        return ResponseEntity.ok("i am admin user");

    }

    @GetMapping("/public")
    public ResponseEntity<String> publicuser(){
        return ResponseEntity.ok("i am public user");

    }

}
