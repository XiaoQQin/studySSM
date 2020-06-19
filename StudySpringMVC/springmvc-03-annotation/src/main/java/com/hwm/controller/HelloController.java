package com.hwm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {


    @RequestMapping("/hello")
    public String hello(Model model){

        model.addAttribute("msg","Hello,springMvc Annotation");
        return "hello"; //会被驶入解析器处理
    }


//    @RequestMapping(value = "/test/{a}/{b}",method = RequestMethod.GET)
//    public String test(@PathVariable int a, @PathVariable int b, Model model){
//
//        model.addAttribute("msg",a+b);
//        return "hello";
//    }


    @GetMapping("/test/{a}/{b}")
    public String test2(@PathVariable int a, @PathVariable int b, Model model){

        model.addAttribute("msg",a+b);
        return "hello";
    }


    @PostMapping("/test/{a}/{b}")
    public String test3(@PathVariable int a, @PathVariable int b, Model model){

        model.addAttribute("msg",a+b);
        return "hello";
    }
}
