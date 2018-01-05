package com.test.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {



    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String hello(Model model){
        model.addAttribute("msg","aasdasd");
        return "hello";
    }

    @RequestMapping(value="/test/{halo}/{ha}",method= RequestMethod.GET)
    public @ResponseBody
    Map ha(@PathVariable String halo, @PathVariable String ha, ModelMap model){
        Map<String,String> map = new HashMap<>();
        map.put("test","666");
        return map;
    }

}
