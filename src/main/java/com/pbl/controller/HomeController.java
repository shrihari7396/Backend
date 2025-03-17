package com.pbl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String homePage() {
        int[] arr = new int[0];
        int length = arr.length;
        return "This is Home Page!!!";
    }
}
