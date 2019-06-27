<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>号码归属查询</title>

   <jsp:include  page="./projectPages/header.jsp"/>
</head>
<body>
         
         <p id="search"> ${search}</p>

   <table id="list"></table> 
   <div id="pager"></div>

</body>


<script type="text/javascript">  

var search=$("#search").text();
$(function(){
	  pageInit();
	});
	function pageInit(){
	  jQuery("#list").jqGrid(
	      {
	        url : "${pageContext.request.contextPath}/person/bib/"+search,
	        datatype : "json",
	        colNames : [ 'Inv No', '书名', '作者'],
	        colModel : [ 
	                     {name : 'id',index : 'id',width : 200},
	                     {name : 'title',index : 'invdate',width : 500},
	                     {name : 'author',index : 'name asc, invdate',width : 500}
	                   ],
	        rowNum : 10,
	        rowList : [ 10, 20, 30 ],
	        pager : '#pager',             //表格数据关联的分页条，html元素
	        sortname : 'id',              //排序字段名
	        mtype : "get",
	        viewrecords : true,           //显示总记录数
	        sortorder : "desc",           //排序方式：倒序，本例中设置默认按id倒序排序
	        caption : "JSON 实例"
	      });
	  jQuery("#list").jqGrid('navGrid', '#pager', {edit : false,add : false,del : false});
	}
</script>


</html>