package com.test.service.impl;

import com.test.mapper.UserMapper;
import com.test.service.IRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegService implements IRegService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean regUser(String userId,String pwd){
        Boolean flag;
        try {
            flag = userMapper.add(userId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return flag;
    }
}
