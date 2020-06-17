# 使用java的方式配置spring

即不需要使用xml文件来进行配置，完全有java来做


实体类

```java

//这里注解的意思，就是陪spring接管了，注册到容器中
@Component
public class User {

    @Value("hwmm")//注入属性值
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
```
  
配置文件
```java
@Configuration //这个也会被Spring容器中注册，
//Configuration意思是一个配置类，相当于Beans

@ComponentScan("com.hwm.pojo") //相当于扫描包

//相当于之前的import标签，引入其他配置
@Import(MyConfig2.class)
public class MyConfig {


    //注册一个bean,就相当于之前的bean标签，id为这个方法的名字，class属性为返回值
    @Bean
    public User getUser(){
        return new User();
    }
}

```


测试类
```java
public class myTest {

    public static void main(String[] args) {
        //使用java配置的话，需要使用AnnotationConfigApplicationContext生成ApplicationContext对象
        ApplicationContext context=new AnnotationConfigApplicationContext(MyConfig.class);

        User getUser = context.getBean("getUser", User.class);

        System.out.println(getUser);
    }
}

```

这种纯java在springboot中随处可见