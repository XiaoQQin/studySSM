# spring AOP面向切面

首先创建项目需要导入相关依赖
```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.4</version>
</dependency>
```


## 1. 方式一
使用spring的api接口  
xml文件的配置  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="userService" class="com.hwm.service.UserServiceImpl"/>
    <bean id="beforeLog" class="com.hwm.log.BeforeLog"/>
    <bean id="afterLog" class="com.hwm.log.AfterLog"/>

    <!--方法一：使用原生spring api接口的-->
    <!--配置Aop:需要导入aop的约束-->
    <aop:config>
        <!--切入点 expression-->
        <aop:pointcut id="pointcut1" expression="execution(* com.hwm.service.UserServiceImpl.*(..))"/>

        <!--执行环绕增加-->
        <aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut1"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut1"/>

    </aop:config>


</beans>
```

代理对象(中介)执行的方法

在目标方法执行之间执行
```java
public class BeforeLog implements MethodBeforeAdvice {
    /**
     *
     * @param method 要执行目标对象的方法
     * @param objects //方法内的参数
     * @param o  // 目标对象
     * @throws Throwable
     */
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println(o.getClass().getName()+"的"+method.getName()+"被执行");
    }
}
```
在目标方法执行之后执行
```java
public class AfterLog implements AfterReturningAdvice {

    /**
     *
     * @param returnValue 目标方法执行后返回值
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行了"+method.getName()+"方法，返回结果为："+returnValue);
    }
}
```
得到的对应结果  
```
com.hwm.service.UserServiceImpl的add被执行
增加了一个用户
执行了add方法，返回结果为：null
```
## 2. 方式二，自定义类实现Aop

自定义类
```java
public class DiyPointCut {
    public void before(){
        System.out.println("方法执行前");
    }

    public void after(){
        System.out.println("方法执行后");
    }
}
```
然后在xml文件中进行配置

```xml
    <!--方法二：自定义类-->
    <bean id="diyPointCut" class="com.hwm.diy.DiyPointCut"/>
    <aop:config>

        <!--自定义一个切面： ref 切面类-->
        <aop:aspect ref="diyPointCut">

            <!--切入点-->
            <aop:pointcut id="pointcut2" expression="execution(* com.hwm.service.UserServiceImpl.*(..))"/>

            <!--通知-->

            <aop:before method="before" pointcut-ref="pointcut2"/>
            <aop:after method="after" pointcut-ref="pointcut2"/>
        </aop:aspect>
    </aop:config>
```

## 方法三 使用注解


xml文件中，只需要添加注解的支持
```xml
    <!--方式三-->
    <bean id="annotationPointCut" class="com.hwm.diy.AnnotationPointCut"/>
    <!--添加注解支持-->
    <aop:aspectj-autoproxy/>
```

自己定义的类，使用注解
```java
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
```

得到相应的结果
```
环绕前
方法执行前
增加了一个用户
方法执行后
环绕后

void com.hwm.service.UserService.add()
```