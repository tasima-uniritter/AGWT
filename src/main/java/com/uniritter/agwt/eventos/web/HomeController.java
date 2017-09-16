package com.uniritter.agwt.eventos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping(value="/")
    String home() {

        return "index";
    }
}
