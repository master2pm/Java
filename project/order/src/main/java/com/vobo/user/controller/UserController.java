package com.vobo.user.controller;

import com.vobo.user.entity.User;
import com.vobo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    String home(){
        String temp = "12345678910";
        try {
          int test =  Integer.parseInt(temp.substring(0,5)) + Integer.parseInt(temp.substring(5));
          System.out.println(test);
        }catch(Exception e){
            System.out.println("GG");
        }
        System.out.println("啦啦啦");
        return "index";
    }

    @RequestMapping("/register")
    String register(){ return "user/register"; }

    @RequestMapping("/login")
    String login(){
//        @PathVariable(value = "username")String username,@PathVariable(value = "password")String password
        User user = userService.sel("12312312123","qqq");
        System.out.println(user);
        return "index";
    }

    @RequestMapping("/register/add")
    String add(){
        Boolean flag;
        User user = new User();
        user.setBith("1909-12-21");
        user.setGender("nv");
        user.setIdcard("12312312312312");
        user.setName("asd");
        user.setPassword("qqq");
        user.setRealname("阿达数独");
        user.setPhone("12312312123");
        user.setEmail("123123@123.com");
        flag = userService.addUser(user);
        System.out.println(flag);
        return "index";
    }

}
