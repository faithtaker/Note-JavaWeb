package com.example.tliasproject.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.tliasproject.pojo.Result;
import com.example.tliasproject.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

//        1.拿到请求的Url
        String reqURL = req.getRequestURI();
        log.info("请求的url:{}", reqURL);

//        2.判断是不是登录   如果是就放行,结束
        if (reqURL.contains("/login")) {
            log.info("登录请求,放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

//        3.拿到token
        String jwt = req.getHeader("token");

//        4.token是否为空如果为空直接不给
        if (!StringUtils.hasLength(jwt)) {
            log.info("Token长度为0 返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
//            到了这一步还不行,要把这个错误结果变成JSON格式 送到Response里面前端才能收到
//            因为这个不是Controller,不能自动变成Json格式 所以要自己写 这里用到了阿里巴巴的工具类fastJson
//            Maven里面配置了就行
            String notLogin = JSONObject.toJSONString(error);
            servletResponse.getWriter().write(notLogin);
            return;
        }

//        5.token是否有效
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("token检测无效 返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }


//        6.放行
        log.info("一切正常,放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
