package com.test.test.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.test.test.mapper.UserMapper;
import com.test.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean addUser(Map map) {
        Boolean flag ;
        try {
            flag = userMapper.addUser(map);
            System.out.println(flag);
            flag = true;
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

}
