package com.example.tliasproject.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 基于注解进行切面编程
 * 这个注解相当于标记了用哪个aop的方法,要用的时候在类上面@一下这个注解就行
 */

@Retention(RetentionPolicy.RUNTIME) //何时生效? 运行时有效
@Target(ElementType.METHOD) //技能作用范围? 作用于[方法]上
public @interface Log {
//    这个只是个标记 真正的业务交给aop切面类来写~
}
