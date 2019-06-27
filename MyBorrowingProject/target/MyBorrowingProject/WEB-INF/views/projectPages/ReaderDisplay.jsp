<%--
  Created by IntelliJ IDEA.
  User: 王
  Date: 2018/10/15
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>读者列表</title>

    <jsp:include  page="header.jsp"/>

    <script type="text/javascript">
        $(function(){
            $(".delete").click(function(){
                var href = $(this).attr("href");
                $("#form_delete").attr("action", href).submit();
                return false;
            });
        });
    </script>

    <script>
        $(document).ready(function(e) {
            $(".but").click(function(e) {
                $("#mytable").toggle();
            });
        });
    </script>
</head>
<body>

<form action="${path}/reader/readers" method="get">
    <input type="submit" value="显示所有读者">
</form>

<input type="button" class="but" value="隐藏/显示Table" />
<br><br>
<a href="${path}/reader/reader">新增</a> <br><br>

<a href="${path}/person/persons">跳转到person页面</a> <br><br>

<a href="${path}/book/books">跳转到book页面</a> <br><br>

<table id="jqGrid"></table>
<div id="jqGridPager"></div>

<div id="mytable">

    <table border="1">
        <thead>
        <tr>
            <th>id</th>
            <th>用户名</th>
            <th>密码</th>
            <th>姓名</th>
            <th>学院</th>
            <th>专业</th>
            <th>性别</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${readers}" var="r">
            <tr>
                <td>${r.id}</td>
                <td>${r.username}</td>
                <td>${r.password}</td>
                <td>${r.name}</td>
                <td>${r.academy}</td>
                <td>${r.specialty}</td>
                <td>${r.gender}</td>
                <td>
                    <!-- 通过<a href>的超链接方式我们只能发送get请求对于form来说只能发get和post请求，
                    如果要发delete和put，则需要伪装成post请求，再在handler处理类中进行解析处理。 -->
                    <a href="${path}/reader/reader/${r.id}">更新</a>
                    <form action="" method="POST" id="form_delete">
                        <input type="hidden" name="_method" value="DELETE"/>
                    </form>
                    <a class="delete" href="${path}/reader/reader/${r.id}">删除</a>
                    <button class="addListBtn">加入清单</button>

                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

</div>

</div>

<script src="${path}/js/project/ReaderDisplay.js"></script>
</body>
</html>
