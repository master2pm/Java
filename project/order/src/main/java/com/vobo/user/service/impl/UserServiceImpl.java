package com.vobo.user.service.impl;

import com.vobo.user.entity.User;
import com.vobo.user.mapper.UserMapper;
import com.vobo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean addUser(User user) {
        Boolean flag;
        try {
            flag = userMapper.addUser(user);
            System.out.println(flag);
        }catch (Exception e){
            System.out.println(e);
            flag = false;
        }
        return flag;
    }

    @Override
    public User sel(String id, String password) {
        User user;
        Map map = new HashMap();
        String type = "";
//        map.put("email",null);
//        map.put("phone",null);
//        map.put("name",null);
        if(id.indexOf("@")!=-1){
            type="email";
        }else{
            try {
                int test = Integer.parseInt(id.substring(0,5)) + Integer.parseInt(id.substring(5));
                if(id.length()==11){
                    type = "phone";
                }
            }catch(Exception e){
                type = "name";
            }
        }
        System.out.println(type);
        map.put(type,id);
        map.put("password",password);
        try {
            user = userMapper.sel(map);
        }catch (Exception e){
            e.printStackTrace();
            user = null;
        }

        return user;
    }
}
