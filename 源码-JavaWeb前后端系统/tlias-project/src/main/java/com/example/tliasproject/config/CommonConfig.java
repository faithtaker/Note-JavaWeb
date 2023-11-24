package com.example.tliasproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
    //声明第三方bean
//    @Bean//将当前方法的返回值对象交给Ioc容器管理，成为Ioc容器bean
//    //通过Bean.注解的name/value属性指定bean名称，如果未指定，默认是方法名
//    public SAXReader saxReader() {
//        return new SAXReader;
//    }
}
