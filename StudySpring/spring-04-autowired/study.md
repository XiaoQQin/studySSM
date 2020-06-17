#自动装配

## 常用方式

### 使用xml文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
        <bean id="cat" class="com.hwm.pojo.Cat"/>
        <bean id="dog" class="com.hwm.pojo.Dog"/>
        
        
</beans>
```
在bean后面有  **autowire** 属性，可以设置自动装配的方式
- byName

```xml
    <bean id="people" class="com.hwm.pojo.People" autowire="byName">
        <property name="name" value="hwm"/>
    </bean>
```
byName会自动在容器上下文中查找和自己属性对象**名称**相同的bean id,名字不相同就不能自动装配

- byType
```xml
    <bean id="people" class="com.hwm.pojo.People" autowire="byType">
        <property name="name" value="hwm"/>
    </bean>
```
byType: 会自动在容器上下文中查找和自己对象类型相同的bean id
,如果上下文有多个对象就会出错


### 使用注解

```
//如果定义required 为false，可以为null,不然不为
@Autowired(required = true)
private Cat cat;

//当spring中有多个对象，使用Qualifier可以指定某个对象
@Autowired
@Qualifier(value = "dog222")
private Dog dog;
```
在类的属性或者set方法上使用 **@Autowired **。属性值默认不为空，也就是
Autowired如果定义required 为false，可以为null,不然不能为空  
  
可以配合使用@Qualifier标签来指定装配某个对象
 