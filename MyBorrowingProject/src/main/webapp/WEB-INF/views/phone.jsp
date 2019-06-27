<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>号码归属查询</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ui.jqgrid.css">  
   <link rel="stylesheet" type="text/css"   href="${pageContext.request.contextPath}/css/jquery-ui.css">  
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>  
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/i18n/grid.locale-cn.js"></script>  
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqGrid.min.js"></script>  
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tablednd.js"></script> 
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.multiselect.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.contextmenu.js"></script>
</head>
<body>
    <p id="phone"> ${phone}</p>    
</body>

</html>