<%--
  Created by IntelliJ IDEA.
  User: hwm
  Date: 2020/6/23
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改书籍</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改书籍</small>
                </h1>
            </div>
        </div>
    </div>


    <form action="${pageContext.request.contextPath}/books/updateBook" method="post">
        <!--前端隐藏域-->
        <input type="hidden" name="bookId" value="${book.bookId}">
        <div class="form-group">
            <label >书籍名称</label>
            <input  type="text" name="bookName" value="${book.bookName}" class="form-control" required>
        </div>
        <div class="form-group">
            <label >书籍数量</label>
            <input   name="bookCount" value="${book.bookCount}" class="form-control" required>
        </div>
        <div class="form-group" aria-disabled="true">
            <label >书籍描述</label>
            <input  name="detail" value="${book.detail}" class="form-control" required>
        </div>

        <div class="form-group">

            <input type="submit"value="修改书籍" class="form-control" >
        </div>

    </form>
</div>
</body>
</html>
