<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--  1.引入样式文件 -->
<link rel="stylesheet" href="lib/layer/ui/css/layui.css" media="all">
</head>
<!-- 2.引入js文件 -->
<script src="lib/layer/ui/layui.js"></script>
<body>
	<!-- 这个div专门用来 显示分页导航条-->
	<div id="test1"></div>
	<script>

// 初始化的分页导航条
layui.use('laypage', function(){
  var laypage = layui.laypage;
  	laypage.render({
	    elem: 'test1' // 显示分页导航条元素的id
	    ,count: "${page.total}" // 总条数
	    ,limit:"${page.pageSize}" // 每页显示5条
  		,curr:"${page.pageNum}" // 当前页
	    ,layout: ['prev', 'page', 'next', 'limit','count']// 布局
	    ,jump: function(obj, first){ // 
		        //obj:分页对象
		        console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
		        console.log(obj.limit); //得到每页显示的条数
		        //首次不执行
		        if(!first){
		        	location.href="${url}currentPage="+obj.curr+"&pageSize="+obj.limit; //UserServlet/getUserPage
		        }
	       	}
	  });
});
</script>
</body>
</html>