# 使用springMVC注解开发

之前关于springMVC的开发都是基于配置文件实现的，基于注解开发变得非常简单



## 一、配置web.xml
还是和之前一样
```xml
<servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springmvc-servlet.xml</param-value>
    </init-param>

    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

## 配置 springmvc-servlet.xml
```xml
<!--扫描包，让注解生效-->
<context:component-scan base-package="com.hwm.controller"/>

<!--让springMvC不处理静态资源-->
<mvc:default-servlet-handler/>
<!--让mvc自动注入处理映射器和处理适配器的实例-->
<mvc:annotation-driven/>


<!--视图解析器-->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/jsp/"/>
    <property name="suffix" value=".jsp"/>
</bean>
```
使用spring的注解，要设置扫描的包，然后配置springMVC会自动注入处理映射器和处理适配器，
自己只需要在文件中配置视图解析器

## 编写controller

在上文中指定的包中建立HelloController
```java
@Controller
public class HelloController {


    @RequestMapping("/hello")
    public String hello(Model model){

        model.addAttribute("msg","Hello,springMvc Annotation");
        return "hello"; //会被驶入解析器处理
    }
}
```

这样就可以了



# 使用Resutful
Resutful是一种资源定位和资源材质风格，可以将网页的请求参数整合到请求地址里。
可以使软件更加简洁，安全，更有层次感。  
  
寻常我们在controller中
```java
@RequestMapping("/test")
public String test(int a,int b,Model model){
    int res=a+b;
    model.addAttribute("msg",a+b);
    return "hello";
}
```
通常要使用 http://localhost:8080/test?a=1&b=2 这个请求地址  
  
使用Restful风格后,即表明参数就在连接里。可以使用 http://localhost:8080/test/1/2
```java
@RequestMapping("/test/{a}/{b}")
public String test(@PathVariable int a, @PathVariable int b, Model model){
    int res=a+b;
    model.addAttribute("msg",a+b);
    return "hello";
}
```

还可以让相同的请求连接(http://localhost:8080/test/1/2)表示不同的请求方法：  
  
 - @GetMapping("/test/{a}/{b}") 或者 @RequestMapping(value = "/test/{a}/{b}",method = RequestMethod.GET)
 - @GetMapping("/test/{a}/{b}") 或者 @RequestMapping(value = "/test/{a}/{b}",method = RequestMethod.POST)