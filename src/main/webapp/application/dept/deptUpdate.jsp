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
	<form action="sdsdsds" method="post" class="form form-horizontal" id="form-dept-add">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>部门名称:</label>
			<div class="formControls col-5">
				<input type="hidden" name="id" value="${dept.id}"/>
				<input type="text" class="input-text" value="${dept.dname}"
				 placeholder="" id="dname" name="dname" datatype="*2-16" nullmsg="部门名不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>是否可用:</label>
			<div class="formControls col-5 skin-minimal">
				<div class="radio-box">
					<input type="radio"	 id="dstate-1" name="dstate" value="1" datatype="*" nullmsg="请选择是否可用">
					<label for="sex-1">可用</label>
				</div>
				<div class="radio-box">
					<input type="radio" id="dstate-2" name="dstate" value="0">
					<label for="sex-2">不可用</label>
				</div>
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>父部门:</label>
			<div class="formControls col-5">
				<input type="hidden" class="input-text" value="${dept.dparentid}" id="dparentid" name="dparentid">
				
				<input type="text" name="dparentname" value="${dept.parentname}" id="dparentname" datatype="*" nullmsg="父部门不能为空"/>
				
				<input type="button" class="btn btn-default" value="选择" onclick="selectParentDept()">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>显示顺序:</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" name="dindex" id="dindex"
				 value="${dept.dindex}" datatype="n" nullmsg="请输入显示顺序">
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3">部门描述:</label>
			<div class="formControls col-5">
				<textarea name="ddesc" cols="" rows="" class="textarea"
				  placeholder="说点什么...100个字符以内" dragonfly="true"
				   onKeyUp="textarealength(this,100)">${dept.ddesc}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
			</div>
			<div class="col-4"> </div>
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
 <script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
$(function(){
	
	//处理部门状态
	var deptState = "${dept.dstate}";
	
	if(deptState == "1"){
		$("#dstate-1").attr("checked",true);
	}else{
		$("#dstate-2").attr("checked",true);
	}
	
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-dept-add").Validform({
		tiptype:2,
		callback:function(form){  //表单全部验证通过后调用
			submit("deptController/update",form);
			return false;         //返回true表单会提交,返回false表单不会提交
		}
	});
	
});

	

</script>
</body>
</html>