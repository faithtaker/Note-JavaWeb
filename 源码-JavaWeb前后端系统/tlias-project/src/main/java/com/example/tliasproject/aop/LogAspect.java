package com.example.tliasproject.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.tliasproject.mapper.OperateLogMapper;
import com.example.tliasproject.pojo.OperateLog;
import com.example.tliasproject.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 切面类,业务是:记录操作日志
 * 封装到注解  @Log  里面
 */
@Slf4j
@Component
@Aspect //切面类 也就是相当于AOP的实体类
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper; //得调用里面的方法操作数据库
    @Autowired
    HttpServletRequest request; //拿到http请求对象 也就是为了拿jwt令牌~

    @Around("@annotation(com.example.tliasproject.anno.Log)")//环绕通知 需要填[形参]才能用到作用的方法捏
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
//        写业务了哥们,想拿[属性]直接在[jwt令牌]那里拿,就很方便
//        OK开始拿jwt令牌
        String jwt = request.getHeader("token");
//        解析一下
        Map<String, Object> claims = JwtUtils.parseJWT(jwt);
//        操作人的ID
        Integer operateUser = (Integer) claims.get("id");
//        操作时间
        LocalDateTime operateTime = LocalDateTime.now();

//操作类名
        String className = joinPoint.getTarget().getClass().getName();
// 操作方法名
        String methodName = joinPoint.getSignature().getName();
// 操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
//操作返回值(必须是JSON格式才行,所以要转一下 - 用fastJSON)
        long begin = System.currentTimeMillis();
//        必须拿到原始目标方法的对象,因为哪怕没有业务写也要return回去啊
        Object result = joinPoint.proceed();
        String returnValue = JSONObject.toJSONString(result);
// 操作耗时
        long end = System.currentTimeMillis();
        long costTime = end - begin;

//        最后总结:记录操作日志
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);//构造的时候填进去
        operateLogMapper.insert(operateLog);
        log.info("操作日志:{}",operateLog);

        return result;
    }
}
