# spring mybatis 整合

## 导入配置文件
和之前mybatis导入的一样，还需要的额外的包
```xml
<!--spring 框架-->
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.7.RELEASE</version>
</dependency>

<!--spring操作数据库，还要导入spring-jdbc-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.7.RELEASE</version>
</dependency>

<!--支持Aop-->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.6.8.RELEASE</version>
</dependency>

<!--最重要的包mybatis-spring -->
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>2.0.5</version>
</dependency>
```


## 第一种方式
自己的理解也就是关于mybatis 中的SeqSession 和 Mapper交给spring来进行管理

定义实体类
```java
@Data
public class User {
    private int id;
    private String name;
    private String pwd;
}
```
定义实体类的Mapper接口
```java
public interface UserMapper {

    public List<User> selectUser();
}
```
实体类的Mapper的xml文件
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace会绑定一个Dao接口-->
<mapper namespace="com.hwm.mapper.UserMapper">

    <select id="selectUser" resultType="user">
        select * from mybatis.user
    </select>
</mapper>
```


接下来最重要的是mybatis中关于数据库configuration的配置完全交给spring来进行配置.
可将该文件命名为 spring-dao.xml，表明是一个数据库操作文件配置，后续在applicationContext中直接引用。
  
该配置文件中，主要是生成datasource SqlSessionFactory sqlSessionTemplate这些类的
bean，和mybatis中的一一对应
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--DataSource:使用spring的数据源替换mybatis的配置
 在这里使用spring提供的hdbc:org.springframework.jdbc.datasource.DriverManagerDataSource-->

    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">

        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=utf8&amp;serverTimezone=UTC"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </bean>
    <!--sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource" />

        <!--绑定mybatis配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="com/hwm/mapper/*.xml"/>
        

    </bean>

    <!--得到sqlSession-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--只能通过构造函数注入-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>

</beans>
```


最后在一个项目总的applicationContext.xml 引用这个spring-dao.xml，由于spring需要处理
那么需要一个类来实现UserMapper这个借口
```java
public class UserMapperImpl implements UserMapper {
    private SqlSessionTemplate sqlSessionTemplate;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<User> selectUser() {

        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);

        return mapper.selectUser();
    }
}
```
使用set注入，最后在applicationContext.xml中生成这个具体操作类的bean
```xml
    <import resource="spring-dao.xml"/>


    <!--得到相应的mapper-->
    <bean id="userMapper" class="com.hwm.mapper.UserMapperImpl">
        <property name="sqlSessionTemplate" ref="sqlSession"/>
    </bean>
```


测试类
```java
    @Test
    public void testMybatisSpring(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);

        List<User> userList = userMapper.selectUser();

        for (User user : userList) {
            System.out.println(user);
        }
    }
```



## 第二种方法

使用 SqlSessionDaoSupport
在编写UserMapper这个借口的实现类是继承 SqlSessionDaoSupport即可，既可以直接得到
sqlSession

```java

public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper  {
    public List<User> selectUser() {
        SqlSession sqlSession = getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
```