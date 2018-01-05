package com.gzbigone.messagepatform.controller;

import com.gzbigone.messagepatform.entity.Jurisdiction;
import com.gzbigone.messagepatform.entity.User;
import com.gzbigone.messagepatform.service.impl.RegService;
import com.gzbigone.messagepatform.utils.HttpClientUtil;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RegService regService;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public Object Index(HttpServletRequest request, HttpServletResponse response) {
//        Jurisdiction jurisdiction = regService.getJurisdiction();
        User user = (User)request.getServletContext().getAttribute("user");
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        Map org = httpClientUtil.getOrgById(user.getOrgTemp(), user.getToken());
        Map<String,Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("accout",user.getAccount());
        map.put("job",user.getJob());
        map.put("org",org);
        map.put("permissions",user.getDomains());

        return map;
    }

}
