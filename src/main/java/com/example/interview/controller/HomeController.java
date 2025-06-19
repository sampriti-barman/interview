package com.example.interview.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return ("<h1>Hello</h1");
    }

    //@PreAuthorize("hasRole('user')")
    @GetMapping("/user")
    public String user() {
        return ("<h1>Hello user</h1");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Hello admin</h1");
    }

}
