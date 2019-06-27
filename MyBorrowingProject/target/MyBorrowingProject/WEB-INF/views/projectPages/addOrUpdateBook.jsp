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
    <title>添加/修改书本</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
  </head>  
  <body>    
    
     <!-- 1.使用form标签可以更加快速的开发表单页面，而且可以方便的进行表单值的回显 -->
    <!-- 2.注意：可以通过modelAttribute属性绑定的模型属性，若没有指定该属性，则默认从request域对象中
    读取command的表单bean，若该属性值也不存在，则发生错误 -->
    <form:form action="${pageContext.request.contextPath}/book/book" method="post"
    modelAttribute="book">
    <!-- path属性对应html表单标签的name属性值 -->
    <!-- 对于_method不能使用form:hidden标签，因为modelAttribute对应的bean中没有_method这个属性，所以用以下格式 -->
    <c:if test="${book.id !=null }">
    <form:hidden path="id"/>
    <input type="hidden" name="_method" value="PUT"/>
    </c:if>
     书名:<form:input path="title"/><br>
     作者:<form:input path="author"/><br>
     <input type="submit" value="确定">  
    </form:form>
  </body>  
</html>  