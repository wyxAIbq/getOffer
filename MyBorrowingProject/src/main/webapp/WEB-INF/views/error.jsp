<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <script type="text/javascript" src="../js/jquery-1.4.3.js"></script>  
    <script type="text/javascript">
       $("#btn").click(function(){
    	   alert(11);
       });
     </script>
	<h3>登录失败</h3>
	<a href="${pageContext.request.contextPath}/index.jsp">重新登录</a>
	
	<form action="" method="POST" id="form">
                                 <input type="hidden" name="_method" value="DELETE"/>
                                </form> 
                                 <a class="delete" href="${pageContext.request.contextPath}/person/${p.id}/person">删除</a> 
</body>
</html>