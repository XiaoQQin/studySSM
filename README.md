# SSM框架整合

## 1. 建立测试数据库
```sql
create database ssmbuild
use ssmbuild

create table books(
 bookId int(10) not null auto_increment primary key comment '书ID',
 bookName varchar(100) not null COMMENT '书名',
 bookCount int(10) not null COMMENT '书的数量',
 detail varchar(100) not null COMMENT '描述'
)engine=INNODB DEFAULT CHARSET=utf8  

insert into books(bookId,bookName,bookCount,detail) VALUES
(1,'java',10,'detail1'),
(2,'phh',2000,'描述2222'),
(3,'python',29999,'大顶顶顶顶顶定多多');
```

## 2. 建立Maven项目，导入相关依赖
在maven项目中的pom.xml文件中导入相关依赖，并且建立项目的结构包：dao、pojo、service、controller 
  
## 3. mybatis
### 3.1 配置文件
在resources目录下创建mybatis-config.xml配置文件,关于数据库的连以及sqlSeesionFactory交由Spring来做，因此mybatis-config.xml文件中,设置别名，
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--设置别名,类名会变为小写-->
    <typeAliases>
        <package name="com.hwm.pojo"/>
    </typeAliases>

</configuration>
```
在resources目录下创建dp.properties文件，存储数据库连接信息
```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/ssmbuild?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
jdbc.username=root
jdbc.password=root
```
### 3.2 创建相关类
-  创建pojo类Books,为了方便，类中属性与表中字段名一致，使用了lombok标签 。
   ```java
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class Books {
       private int bookId;
       private String bookName;
       private int bookCount;
       private String detail;
   }

   ```
-  在dao包中创建实体类操作接口BookMapper，以及相关mybatis配置文件BooksMapper.xml
   ```java
   public interface BooksMapper {
    //增
    int addBook(Books book);
    //删
    int deleteBookById(int bookId);
    //查
    Books selectBookById(int bookId);
    List<Books> selectAllBooks();
    //改
    int updateBook(Books book);
    }
   ```
   BooksMapper.xml文件
   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <mapper namespace="com.hwm.dao.BooksMapper">
       <insert id="addBook" parameterType="books">
           insert into books(bookName, bookCount, detail) values (#{bookName}, #{bookCount}, #{detail})
       </insert>

       <delete id="deleteBookById">
           delete  from books where bookId=#{bookId}
       </delete>

       <select id="selectBookById" resultType="books">
           select * from books where bookId=#{bookId}
       </select>
       <select id="selectAllBooks" resultType="books">
           select * from books
       </select>

       <update id="updateBook" parameterType="books">
           update books set 
           bookName=#{bookName},bookCount=#{bookCount},detail=#{detail}
           where bookId=#{bookId}
       </update>
   </mapper>
   ```
## 4. spring
关于spring主要是对dao和service包中进行配置，dao包主要用户数据库的操作，service包主要是调用dao包，此外在此基础上添加一些其他操作。  
### 4.1 配置dao包
-  在resource包下创建spring-dao.xml文件
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xmlns:context="http://www.springframework.org/schema/context" 
          xmlns:tx="http://www.springframework.org/schema/tx"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/aop
           https://www.springframework.org/schema/aop/spring-aop.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/tx
           https://www.springframework.org/schema/tx/spring-tx.xsd">

       <!--加载配置文件-->
       <context:property-placeholder location="classpath:db.properties"/>

       <!--mysql数据源：数据库连接池-->
       <bean id="datasource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
           <property name="driverClass" value="${jdbc.driver}"/>
           <property name="jdbcUrl" value="${jdbc.url}"/>
           <property name="user" value="${jdbc.username}"/>
           <property name="password" value="${jdbc.password}"/>

           <!--c3p0连接池的私有属性-->
           <property name="maxPoolSize" value="30"/>
           <property name="minPoolSize" value="10"/>
           <!--关闭连接后不自动commit-->
           <property name="autoCommitOnClose" value="false"/>
           <!--获取连接池超时时间-->
           <property name="checkoutTimeout" value="10000"/>
           <!--获取连接失败时重连次数-->
           <property name="acquireRetryAttempts" value="2"/>
       </bean>

       <!--sqlSessionFactory-->
       <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
           <property name="dataSource" ref="datasource" />
           <!--绑定mybatis配置文件-->
           <property name="configLocation" value="classpath:mybatis-config.xml"/>
           <!--相关mapper文件-->
           <property name="mapperLocations" value="com/hwm/dao/*.xml"/>
       </bean>

       <!--配置声明式事务-->
       <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
           <property name="dataSource" ref="datasource"/>
       </bean>

       <!--结合Aop实现事务的插入-->
       <!--配置事务通知-->
       <tx:advice id="txAdvice" transaction-manager="transactionManager">
           <!--给哪些方法配置事务-->
           <tx:attributes>
               <tx:method name="*" propagation="REQUIRED"/>
           </tx:attributes>
       </tx:advice>
       <!--配置事务切入-->
       <aop:config>
           <aop:pointcut id="txPointCut" expression="execution(* com.hwm.dao.*.*(..))"/>
           <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
       </aop:config>
   </beans>
   ```
-  在dao包下创建BooksMapperImpl类，调用mybatis的sqlSession具体实现数据库操作，采用的是继承SqlSessionDaoSupport的方式 [使用SqlSessionDaoSupport]  (https://mybatis.org/spring/zh/sqlsession.html#SqlSessionDaoSupport)
   ```java
   public class BooksMapperImpl extends SqlSessionDaoSupport implements BooksMapper {
       public int addBook(Books book) {
           BooksMapper mapper = getSqlSession().getMapper(BooksMapper.class);

           int i = mapper.addBook(book);
           return i;
       }

       public int deleteBookById(int bookId) {
           return getSqlSession().getMapper(BooksMapper.class).deleteBookById(bookId);
       }

       public Books selectBookById(int bookId) {
           return getSqlSession().getMapper(BooksMapper.class).selectBookById(bookId);
       }

       public List<Books> selectAllBooks() {
           return getSqlSession().getMapper(BooksMapper.class).selectAllBooks();
       }

       public int updateBook(Books book) {
           return getSqlSession().getMapper(BooksMapper.class).updateBook(book);
       }
   }
   ```
   接下来即可使用junit进行测试，看是否配置成功
-  另一种方式：不需要自己手动实现BooksMapperImpl，在spring-dao.xml配置文件中添加如下配置,那么spring就可以直接调用
    ```xml
    <!--添加如下的配置，那么就不要向上述一样实现BooksMapperImpl，spring会自动帮我们生成-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <property name="basePackage" value="com.hwm.dao"/>
    </bean>
    ```
### 4.2 配置service包
