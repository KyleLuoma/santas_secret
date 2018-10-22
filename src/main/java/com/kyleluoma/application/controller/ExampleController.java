package com.kyleluoma.application.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ExampleController {

    @RequestMapping("/greeting/")
    public String index() {
        return "<h1>Greetings from Santas Secret!</h1>";
    }
}
