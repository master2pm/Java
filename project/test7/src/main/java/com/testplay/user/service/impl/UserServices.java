package com.testplay.user.service.impl;

import com.testplay.user.entity.User;
import com.testplay.user.mapper.UserMapper;
import com.testplay.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements UserService{
    @Autowired
    private UserMapper userMapper;


    @Override
    public boolean addUser(User user) {
        Boolean flag ;
        try {
            flag = userMapper.add(user);
        } catch (Exception e){
            flag = false;
        }
        return false;
    }
}
