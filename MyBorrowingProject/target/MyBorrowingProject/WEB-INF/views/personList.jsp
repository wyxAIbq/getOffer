<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<html>  
  <head>  
    <title>用户列表</title>
      <jsp:include  page="projectPages/header.jsp"/>

      <!--  通过获取button的父元素tr中id的值 -->
       <script type="text/javascript">
       $(function(){
       $(".btn").click(function(){
    	   var id = $(this).parents("tr").find("#id").text();
    	   alert(id);
           var obj = {
               "id":111,
               "name":"jerry1",
               "idCard":1,
               "phone":123,
               "address":"haha"
           };
           $.ajax({
    		   type:"post",
    		   /* url没有问题 */
   			   url:"${path}/person/person/"+id,
   			   dataType:"text",
   			   contentType:"application/x-www-form-urlencoded;charset=UTF-8",
   			  
        	   success:function(data)
        	      {
        		   alert(data);
        		   },

        	 });
       });
       });
       
   </script>
   
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
       $(".btn1").click(function(){
    	   //var id = $(this).parents("tr").find("#id").text();

    	    var obj = {     
    			    "id":111,
	                "name":"jerry2",
	                "idCard":1,
	                "phone":123,
	                "address":"haha"
	            }; 
    	    
    	   $.ajax({
    		   type:"post",
    		   /* url没有问题 */
   			   url:"${path}/person/wyx",
   			   dateType:"json",
   			   data:obj, 
        	   success:function(data)
        	      {
        		   alert(data);
        		   },
        	 });   
       });
       });
       
   </script>
    
  </head>  
  <body>
<div id="app">
    <a href="${path}/book/books">返回图书页面</a> <br><br>
<p>{{ message }}</p>
</div>

 <table id="list"></table>  

  <form action="${path}/person/persons" method="get">
  <input type="submit" value="显示所有用户">
  </form> 
  <a href="${path}/person/person">新增</a> <br><br>
  
 
  
        <table border="1">  
            <tbody>  
                <tr>  
                    <th>id</th>
                    <th>名字</th>  
                    <th>身份证</th>  
                    <th>电话</th> 
                    <th>地址</th> 
                    <th>操作</th>  
                </tr>  
                    <c:forEach items="${persons}" var="p">  
                        <tr>  
                            <td id="id">${p.id}</td>
                            <td>${p.name }</td>  
                            <td>${p.idCard }</td> 
                            <td>${p.phone }</td>
                            <td>${p.address }</td> 
                            <td>  
                            <!-- 通过<a href>的超链接方式我们只能发送get请求对于form来说只能发get和post请求，
                                                                                         如果要发delete和put，则需要伪装成post请求，再在handler处理类中进行解析处理。 -->
                                <a href="${path}/person/person/${p.id}">更新</a>
                                <form action="" method="POST" id="form_delete">
                                 <input type="hidden" name="_method" value="DELETE"/>
                                </form>
                                 <a class="delete" href="${path}/person/person/${p.id}">删除</a>
                                  <a href="${path}/person/phone/${p.phone}">手机号归属地查询</a>
                                  
                                 <button class="btn">显示</button>
                                 <button class="btn1">JSON显示</button>
                                 
                            </td>  
                        </tr>  
                    </c:forEach>  
            </tbody>  
        </table>  
        
        <a href="${path}/person/InJqgrid/java">Jqgrid入口页面</a>
        
        <input type="text" name="bib" id="index">
         <a onclick="aClick()" href="" id="search">图书馆检索</a>

 
<script>
function aClick(){
    var search=$("#index").val();
    var searchUrl ="${path}/person/InJqgrid/"+encodeURI(search);   //使用encodeURI编码
    $("#search").attr("href",searchUrl);

};
</script> 


<script>
new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue.js!'
  }
});
</script> 
         
  </body>  
</html>  