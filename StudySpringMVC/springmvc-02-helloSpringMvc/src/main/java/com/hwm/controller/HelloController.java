package com.hwm.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mv=new ModelAndView();

        //封装对象，放在ModelAndView中
        mv.addObject("msg","HelloSpingMvc");
        mv.setViewName("hello");
        return mv;
    }
}
