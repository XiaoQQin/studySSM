<%--
  Created by IntelliJ IDEA.
  User: hwm
  Date: 2020/6/23
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加书籍</title>

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>增加书籍</small>
                </h1>
            </div>
        </div>
    </div>


    <form action="${pageContext.request.contextPath}/books/addBooks" method="post">
        <div class="form-group">
            <label >书籍名称</label>
            <input  type="text" name="bookName" class="form-control" required>
        </div>
        <div class="form-group">
            <label >书籍数量</label>
            <input   name="bookCount" class="form-control" required>
        </div>
        <div class="form-group">
            <label >书籍描述</label>
            <input  name="detail" class="form-control" required>
        </div>
        <div class="form-group">

            <input type="submit"value="添加书籍" class="form-control" >
        </div>

    </form>
</div>

</body>
</html>
