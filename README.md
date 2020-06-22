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
