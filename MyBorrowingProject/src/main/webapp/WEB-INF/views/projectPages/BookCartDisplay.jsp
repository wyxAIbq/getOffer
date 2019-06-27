<%--
  Created by IntelliJ IDEA.
  User: 王
  Date: 2018/10/16
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>

    <title>待送清单</title>
    <jsp:include  page="header.jsp"/>

</head>
<body>
<div id="app">
<p>${reader}</p>

    <div id="update_count" style="display: none">
        <i-button @click="minus">
            <i class="ivu-icon ivu-icon-minus-circled" style="font-size: 18px"></i>
        </i-button>
        &nbsp{{value}}&nbsp
        <i-button @click="add">
            <i class="ivu-icon ivu-icon-android-add-circle" style="font-size: 18px"></i>
        </i-button>
        <i-button @click="submitCount">确定</i-button>
    </div>

<a href="${path}/book/books">返回继续添加</a> <br><br>
<div>
  <form action="" method="POST" id="form_delete">
    <input type="hidden" name="_method" value="DELETE"/>
  </form>
    <i-button @click="update">更新图书数量</i-button>&nbsp&nbsp
    <i-button @click="deleteBook">删除</i-button>&nbsp&nbsp
    <i-button @click="clearBookCart">清空购物车</i-button>&nbsp&nbsp
    <i-button @click="submitBookCart">确认订单</i-button>
    <form action="" method="POST" id="form_submit">
        <input type="hidden" id="bookIds" name="bookIds" value=""/>
    </form>

</div>
<div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
</div>


<script type="text/javascript" charset="UTF-8" src="${path}/js/project/BookCartDisplay.js"></script>
</body>
</html>
