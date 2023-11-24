package com.example.tliasproject.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.tliasproject.pojo.Result;
import com.example.tliasproject.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    /**
     * 前处理
     *
     * @param req     作用：表示当前的HTTP请求对象，可以通过它获取请求的信息，如请求头、请求参数等。
     * @param rsp     作用：表示当前的HTTP响应对象，可以通过它设置响应的信息，如响应头、状态码等。
     * @param handler 作用：表示当前请求要执行的处理器（Controller方法）。在 preHandle 方法中，你可以根据需要对这个处理器进行判断或者处理。
     * @return boolean 作用：表示是否允许请求继续执行。如果返回 true，则继续执行后续的拦截器和处理器（Controller）；如果返回 false，则中断后续的处理，请求不会到达处理器。
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse rsp, Object handler) throws Exception {
//        1.拿到请求的Url
        String reqURL = req.getRequestURI();
        log.info("请求的url:{}", reqURL);

//        2.判断是不是登录如果是就放行,结束
        if (reqURL.contains("/login")) {
            log.info("登录请求,放行");
            return true;
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
            rsp.getWriter().write(notLogin);
            return false;
        }

//        5.token是否有效
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("token检测无效 返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            rsp.getWriter().write(notLogin);
            return false;
        }


//        6.放行
        log.info("一切正常,放行");
        return true;
    }
}

