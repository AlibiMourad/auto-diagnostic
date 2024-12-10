package com.hospital.auto.diagnostic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/illegalArgument")
    public void throwIllegalArgumentException() {
        throw new IllegalArgumentException("Invalid argument");
    }

    @GetMapping("/genericException")
    public void throwGenericException() {
        throw new RuntimeException("Unexpected error");
    }
}
