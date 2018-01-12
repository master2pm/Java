package com.test.test.controller;


import com.test.test.entity.User;
import com.test.test.mapper.UserMapper;
import com.test.test.service.UserService;
import com.test.test.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    String home(){
        return "index";
    }

    @RequestMapping("/wel")
    String wel() { return "login"; }

    @RequestMapping("/login")
    String login(@RequestParam("name")String name,@RequestParam("password")String password){
        System.out.println("name:"+name+"\tpassword:"+password);
        Map map = new HashMap();
        map.put("name",name);
        map.put("password",password);
        System.out.println(userService.addUser(map));
        return "login";
    }

    private String creatMD5(String loginNum){

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(loginNum.getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }
        return new BigInteger(1,md.digest()).toString(16);
    }

}
