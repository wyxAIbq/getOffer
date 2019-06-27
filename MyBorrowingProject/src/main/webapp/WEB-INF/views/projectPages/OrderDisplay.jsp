<%--
  Created by IntelliJ IDEA.
  User: 王
  Date: 2018/11/15
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>订单页面</title>
    <jsp:include  page="header.jsp"/>
</head>
<body>

<div id="app">

    <p id="bookIds">${bookIds}</p>

    <a href="${path}/cart/cart">返回购物车</a> <br><br>

    <div>
        <i-button @click="submitOrder">确认送书</i-button>
        <br><br>
        <i-form :model="formItem" :label-width="80">
            <Form-item label="选择快递箱">
            <Radio-Group v-model="formItem.expressBox">
                <Radio label="1">
                    <span>快递箱1号</span>
                </Radio>
                <Radio label="2">
                    <span>快递箱2号</span>
                </Radio>
            </Radio-Group>
            </Form-item>
        </i-form>
    </div>

    <div>
        <table id="jqGrid"></table>
        <div id="jqGridPager"></div>
    </div>
</div>


<script type="text/javascript" charset="UTF-8" src="${path}/js/project/OrderDisplay.js"></script>

</body>
</html>
