package com.study.kang.bootsecu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value = "/")
    public String rootIndex () {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index")
    public String index () {
        return "index";
    }
}
