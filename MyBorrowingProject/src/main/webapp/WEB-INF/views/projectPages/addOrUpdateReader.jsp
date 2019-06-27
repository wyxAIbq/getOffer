<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>添加/修改读者</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>

<!-- 1.使用form标签可以更加快速的开发表单页面，而且可以方便的进行表单值的回显 -->
<!-- 2.注意：可以通过modelAttribute属性绑定的模型属性，若没有指定该属性，则默认从request域对象中
读取command的表单bean，若该属性值也不存在，则发生错误 -->
<form:form action="${pageContext.request.contextPath}/reader/reader" method="post"
           modelAttribute="reader">
    <!-- path属性对应html表单标签的name属性值 -->
    <!-- 对于_method不能使用form:hidden标签，因为modelAttribute对应的bean中没有_method这个属性，所以用以下格式 -->
    <c:if test="${reader.id!=null }">
        <form:hidden path="id"/>
        <input type="hidden" name="_method" value="PUT"/>
    </c:if>
    用户名:<form:input path="username"/><br>
    密码:<form:input path="password"/><br>
    姓名:<form:input path="name"/><br>
    学院:<form:input path="academy"/><br>
    专业:<form:input path="specialty"/><br>
    性别:<form:input path="gender"/><br>
    <input type="submit" value="确定">
</form:form>
</body>
</html>