# 使用注解开发
在spring4之后，要使用注解的开发必须导入AOP包
并且配置文件中要添加注解的支持
```xml
<!--开启注解的支持-->
<context:annotation-config/>
```

## 1. bean
## 2. 属性如何注入

    ```java
    //等价于<bean id="user" class="com.hwm.pojo.User"/>
    @Component
    public class User {
        
        //等价于<property name="name" value="hwm"/>,也可以在set方法上使用
        @Value("hwm")
        public String name;
    
        public void setName(String name) {
            this.name = name;
        }
    }
    ```  

## 3. 衍生的注解  

    @Component有几个衍生注解，在web开发中会按照MVC三层架构分层
    
      - dao @Repository
      - service @Service
      - controller Controller
      
    这四个注解功能都是一样的，都是将某个类注册到spring中  
  
  
## 4. 自动装配
@Autowired:注入
@nullable 可以为空
## 5. 作用域

   使用@Scope注解,和xml中一样
    ```java
    @Scope("singleton")
    public class User {
    
        //等价于<property name="name" value="hwm"/>,也可以在set方法上使用
        @Value("hwm")
        public String name;
    
        public void setName(String name) {
            this.name = name;
        }
    }
    ```
## 6. 总结  

    xml与注解：
       1) xml更加万能，使用于任何场景，维护简单
       2) 注解不是自己类使用不来，维护相对复杂
    
    
    xml与注解的最佳实现：
       1) xml用来管理Bean
       2) 注解只负责完成属性的注入   



