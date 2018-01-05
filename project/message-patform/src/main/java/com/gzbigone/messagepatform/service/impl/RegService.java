package com.gzbigone.messagepatform.service.impl;

import com.gzbigone.messagepatform.entity.Jurisdiction;
import com.gzbigone.messagepatform.mapper.UserMapper;
import com.gzbigone.messagepatform.service.IRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class RegService implements IRegService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Jurisdiction> getJurisdiction( ) {
        return userMapper.getJurisdiction();
    }
}