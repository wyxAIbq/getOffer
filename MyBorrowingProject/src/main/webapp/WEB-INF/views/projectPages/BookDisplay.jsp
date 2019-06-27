<%--
  Created by IntelliJ IDEA.
  User: 王
  Date: 2018/9/13
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>图书列表</title>

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

    <script type="text/javascript">
        $(function(){
            $(".addListBtn").click(function(){
                var id = $(this).parents("tr").find("#bookId").text();
                $.ajax({
                    type:"post",
                    /* url没有问题 */
                    url:"${path}/list/book/"+id,
                    dataType:"text",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success:function(data)
                    {
                        alert(data+"加入清单成功");
                    },
                });
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
<div id="body">

<form action="${path}/book/books" method="get">
    <input type="submit" value="显示所有书本">
</form>

<input type="button" class="but" value="隐藏/显示Table" />
<br><br>
<a href="${path}/book/book">新增</a> <br><br>

<a href="${path}/person/persons">跳转到person页面</a> <br><br>

    <a href="${path}/reader/readers">跳转到reader页面</a> <br><br>

    <button id="addlistbtn">加入购物车</button>

    <form action="" method="GET" id="form_getlist">
        <input type="submit" value="查看购物车"/>
    </form>

    <div>
        <i-icon custom="i-icon i-icon-shop_fill" size="24" ></i-icon>
        <i-icon custom="i-icon i-icon-publishgoods_fill" size="24" ></i-icon>
        <i-icon custom="i-icon i-icon-financial_fill" size="24" ></i-icon>
    </div>

<div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>

<div id="mytable">

<table border="1">
    <thead>
    <tr>
        <th>id</th>
        <th>书名</th>
        <th>作者</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="b">
        <tr>
            <td id="bookId">${b.id}</td>
            <td>${b.title }</td>
            <td>${b.author }</td>
            <td>
                <!-- 通过<a href>的超链接方式我们只能发送get请求对于form来说只能发get和post请求，
                如果要发delete和put，则需要伪装成post请求，再在handler处理类中进行解析处理。 -->
                <a href="${path}/book/book/${b.id}">更新</a>
                <form action="" method="POST" id="form_delete">
                    <input type="hidden" name="_method" value="DELETE"/>
                </form>
                <a class="delete" href="${path}/book/book/${b.id}">删除</a>
                <button class="addListBtn">加入清单</button>

            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>

</div>

</div>
<script type="text/javascript" charset="UTF-8" src="${path}/js/project/BookDisplay.js"></script>

</body>
</html>