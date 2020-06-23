package com.hwm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ajax")
public class AjaxController {


    @RequestMapping("/test")
    public String test(){
        return "ajaxTest";
    }

}
