package com.gzbigone.messagepatform.config;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Configuration
//@Component
public class CasConfigure {

    @Value("${cas.server-login-url}")
    private String casServerLoginUrl;

    @Value("${cas.server-url-prefix}")
    private String casServerUrlPrefix;

    @Value("${cas.client-host-url}")
    private String serverName;

    /** 退出功能cas   */
    @Bean
    public FilterRegistrationBean singleSignOutFilter() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new SingleSignOutFilter());
        filterRegistration.setEnabled(true);
        /*if(autoconfig.getSignOutFilters().size()>0)
            filterRegistration.setUrlPatterns(autoconfig.getSignOutFilters());
        else  */
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.addInitParameter("casServerUrlPrefix", casServerUrlPrefix);
        filterRegistration.addInitParameter("serverName", serverName);
        filterRegistration.setOrder(1);
        return filterRegistration;
    }


    @Bean
    public FilterRegistrationBean authenticationFilterRegistrationBean() {
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        //authenticationFilter.setFilter(new AuthenticationFilter());
        authenticationFilter.setFilter(new CasFilter());
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerLoginUrl", casServerLoginUrl);
        initParameters.put("serverName", serverName);

        //添加过滤的路径
        initParameters.put("excludePaths", "^/user/.*,^/holiday/.*,^/examinationApproval/.*");

        authenticationFilter.setInitParameters(initParameters);
        authenticationFilter.setOrder(3);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }

    /** 该过滤器负责对Ticket的校验工作，必须启用它 */
    @Bean
    public FilterRegistrationBean ValidationFilterRegistrationBean() {
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerUrlPrefix", casServerUrlPrefix);
        initParameters.put("serverName", serverName);
        authenticationFilter.setInitParameters(initParameters);
        authenticationFilter.setOrder(2);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }

    /** 该过滤器负责实现HttpServletRequest请求的包裹， 比如允许开发者通过HttpServletRequest的getRemoteUser()方法获得SSO登录用户的登录名，可选配置。 */
    @Bean
    public FilterRegistrationBean casHttpServletRequestWrapperFilter() {
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new HttpServletRequestWrapperFilter());
        authenticationFilter.setOrder(4);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }

    /** 该过滤器使得开发者可以通过org.jasig.cas.client.util.AssertionHolder来获取用户的登录名 */
    @Bean
    public FilterRegistrationBean casAssertionThreadLocalFilter() {
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new AssertionThreadLocalFilter());
        authenticationFilter.setOrder(5);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }
}

