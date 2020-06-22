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
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/ssmbuild?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
username=root
password=root
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
