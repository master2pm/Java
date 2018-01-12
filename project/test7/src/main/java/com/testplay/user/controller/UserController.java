package com.testplay.user.controller;

import com.testplay.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.security.MessageDigest;

@Controller
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    String home(){
        return "index";
    }

    @RequestMapping("/login")
    String login(){
        return "login";
    }

    @RequestMapping("/logining")
    @ResponseBody
    Boolean logining(@RequestParam("username")String name,@RequestParam("password")String password){
        String pwd = "";
        return true;
    }

    private String creatMD5(String loginNum){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(loginNum.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new BigInteger(1,md.digest().toString());
    }
}
