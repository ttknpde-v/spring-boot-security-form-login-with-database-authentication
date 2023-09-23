package com.ttknpdev.understand.security.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerApi {
    @GetMapping(value = "/login")
    private String login() {
        return "login";
    }
    @GetMapping(value = "/test")
    private String test() {
        return "test";
    }
}
