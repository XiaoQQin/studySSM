package com.hwm.diy;


// 方法三，使用注解方式实现AOP

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect  //表明这是一个切面类
public class AnnotationPointCut {


    //方法执行前
    @Before("execution(* com.hwm.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("方法执行前");
    }


    @After("execution(* com.hwm.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("方法执行后");
    }

    //环绕，在环绕中可以给定一个参数，代表要切入的点，也就是执行的目标的方法
    @Around("execution(* com.hwm.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");
        Object proceed = jp.proceed();

        System.out.println("环绕后");


        System.out.println("\n\n");
        //获取签名，也就是执行什么方法
        Signature signature = jp.getSignature();
        System.out.println(signature);
    }


}
