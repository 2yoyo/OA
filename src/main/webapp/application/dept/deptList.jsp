<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=request.getContextPath() +"/"%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>

</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
	<div class="text-c">
			<div class="row cl ">
				<div class="formControls col-3">
					登录名: <input type="text" class="input-text" style="width: 250px">
				</div>
				<div class="formControls col-4">
					邮箱： <input type="text" class="input-text" style="width: 250px">
				</div>
				<div class="formControls col-5">
					手机： <input type="text" class="input-text" style="width: 250px">
				</div>
			</div>
			<div class="row cl">
				<div class="cl pd-5">
					<button type="button"
						class="btn btn-success radius" id="" name="">
						<i class="Hui-iconfont">&#xe665;</i> 搜用户
					</button>
				</div>
			</div>	
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
	<a href="javascript:;" onclick="batchDel()" class="btn btn-danger radius">
	<i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
	 <a href="javascript:;" onclick="admin_add('添加部门','application/dept/deptAdd.jsp','600','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加部门</a>
	 </span>
	 </div>
	 
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="10">部门列表</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="40">ID</th>
				<th width="90">部门名称</th>
				<th width="100">父部门名称</th>
				<th width="150">部门描述</th>
				<th width="50">显示顺序</th>
				<th width="50">状态</th>
				<th width="130">加入时间</th>
				<th width="90">是否已启用</th>
				<th width="90">操作</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach items="${page.list}" var="dept">
				<tr class="text-c">
					<td><input type="checkbox" value="${dept.id}" name=""></td>
					<td>${dept.id}</td>
					<td>${dept.dname}</td>
					<td>${dept.parentname}</td>
					<td>${dept.ddesc}</td>
					<td>${dept.dindex}</td>
					<td>${dept.dstate == 1?'可用':'不可用'}</td>
					<td>
						<fm:formatDate value="${dept.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td class="td-status"><span class="label label-success radius">已启用</span></td>
					<td class="td-manage">
						<a style="text-decoration:none" onClick="admin_stop(this,'10001')" href="javascript:;" title="停用">
							<i class="Hui-iconfont">&#xe631;</i>
						</a>
						
						<a title="编辑" href="javascript:;" onclick="admin_edit('部门编辑','deptController/getById/${dept.id}','1','800','500')" class="ml-5" style="text-decoration:none">
						 	<i class="Hui-iconfont">&#xe6df;</i>
						</a>
						  
						<a title="删除" href="javascript:;" onclick="admin_del(this,${dept.id})" class="ml-5" style="text-decoration:none">
						 	<i class="Hui-iconfont">&#xe6e2;</i>
						</a>
					 </td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<jsp:include page="/common/page.jsp"></jsp:include>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>  
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript" src="js/oa_utils.js"></script> 
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/
function admin_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-删除*/
function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.get("deptController/delete/"+id,"",function(data){
			showMsg(data);
 		},"JSON");
	});
}
/*管理员-编辑*/
function admin_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-停用*/
function admin_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*管理员-启用*/
function admin_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:1000});
	});
}

function batchDel(){
	debugger;
	//创建数组装被选择的id
	var idArray = new Array();
	
	//获取用户选择的id
	$(":checkbox:checked").each(function(index,item){
		//用户选择的id放入数组
		idArray.push(item.value);
	});
	
	//创建一个对象
	var param = new Object();
	param.ids = idArray;
	
	//异步把数据传过去
	$.post("deptController/batchDel",param,function(){
		location.reload();
	});
}

</script>
</body>
</html>