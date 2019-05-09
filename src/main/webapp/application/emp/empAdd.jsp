<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=request.getContextPath() +"/"%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
<div class="pd-20">
	<form action="sd" method="post" class="form form-horizontal" id="form-emp-add">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>用户名:</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="admin" placeholder="" 
				id="username" name="username" datatype="*2-8" nullmsg="用户名不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>密码:</label>
			<div class="formControls col-5">
				<input type="password" placeholder="密码" name="password" autocomplete="off" value="admin" 
				class="input-text" datatype="*5-15" nullmsg="密码不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>确认密码:</label>
			<div class="formControls col-5">
				<input type="password" name="repassword" recheck="password" placeholder="密码" autocomplete="off" value="admin" 
				class="input-text" datatype="*5-15" errormsg="两次密码不一致" nullmsg="密码不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>性别:</label>
			<div class="formControls col-5 skin-minimal">
				<div class="radio-box">
					<input type="radio" checked="checked" id="sex-1" name="sex" 
					value="1" datatype="*" nullmsg="请选择性别">
					<label for="sex-1">男</label>
				</div>
				<div class="radio-box">
					<input type="radio" id="sex-2" name="sex" value="0">
					<label for="sex-2">女</label>
				</div>
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>部门:</label>
			<div class="formControls col-5">
				<input type="hidden" class="input-text" value="1" id="dparentid" name="deptid">
				
				<input type="text" name="dparentname" id="dparentname" datatype="*" nullmsg="父部门不能为空"/>
				
				<input type="button" class="btn btn-default" value="选择" onclick="selectParentDept('/deptController/getParentDeptList')">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>手机:</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="15079410769" placeholder="" id="phone" name="phone"  datatype="m" nullmsg="手机不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>邮箱:</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" placeholder="@" name="email" value="admin@qf.com" id="email" datatype="e" nullmsg="请输入邮箱！">
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>入职时间:</label>
			<div class="formControls col-5">
				<input type="text" name="hiredate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'logmin\')||\'%y-%M-%d\'}'})" id="logmin" class="input-text Wdate" style="width:120px;">
			</div>
			<div class="col-4"> </div>
		</div>
		
		
		</div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="lib/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript" src="js/oa_utils.js"></script>
<script type="text/javascript" src="/lib/My97DatePicker/WdatePicker.js"></script>  
 <script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-emp-add").Validform({
		tiptype:2,
		callback:function(form){  //表单全部验证通过后调用
			submit("empController/add",form);
			return false;         //返回true表单会提交,返回false表单不会提交
		}
	});
	
});

</script>
</body>
</html>