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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RegService regService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Object Index(HttpServletRequest request, HttpServletResponse response, @RequestParam("orgId") String orgId) {
        try {
            List<Jurisdiction> jurisdictions = regService.getJurisdiction();
            AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
            HttpClientUtil httpClientUtil = new HttpClientUtil();
            if(principal!=null){
                Map<String, Object> casServerMap = principal.getAttributes();
                //获取用户id
                Integer userId=Integer.parseInt(casServerMap.get("userId").toString());
                String token = httpClientUtil.getToken();
                User user = httpClientUtil.getUserInfo(userId, token);
                user.setToken(token);
                Map orgByIdAndOrgId = (Map) httpClientUtil.getOrgByIdAndOrgId(userId, orgId, token);
                //獲取身份id
                Integer userOrgId = (Integer)orgByIdAndOrgId.get("userOrgId");
                user.setUserOrgId(userOrgId);
                user.setOrgTemp(orgId);
                //獲取domain
                List<Integer> domainIds = httpClientUtil.getAppByUserId(userOrgId, token);
                List domains = new ArrayList();
                for(Jurisdiction jurisdiction : jurisdictions){
                    for(Integer domainId: domainIds){
                        if(jurisdiction.getDomainId().equals(domainId)){
                            Map domainById = httpClientUtil.getDomainById(String.valueOf(domainId), token);
                            domainById.put("Jurisdiction",jurisdiction.getPermission());
                            domains.add(domainById);
                            break;
                        }

                    }
                }
                user.setDomains(domains);
                //存储用户id
                ServletContext context = request.getServletContext();
                context.setAttribute("user", user);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:http://118.89.63.221:8081/cpadmin/Message.html";
    }

}
