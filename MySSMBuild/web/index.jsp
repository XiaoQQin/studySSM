<%--
  Created by IntelliJ IDEA.
  User: hwm
  Date: 2020/6/23
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
    <style>
      a{
        text-decoration: none;
        color: black;
        font-size: 18px;
      }
      h3{
        width: 180px;
        height: 30px;
        margin: 100px auto;
        text-align: center;
        line-height: 30px;
        background: deepskyblue;
        border-radius: 5px;
      }
    </style>
  </head>
  <body>
  <h3>
    <a href="${pageContext.request.contextPath}/books/allBooks">进入书籍页面</a>
  </h3>
  </body>
</html>
