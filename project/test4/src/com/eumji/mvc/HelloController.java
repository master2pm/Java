package com.eumji.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 2017/7/13
 * @time: 15:37
 */
@Controller
public class HelloController {
    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("msg", "Spring 3 MVC Hello World");
        return "hello";
    }
}