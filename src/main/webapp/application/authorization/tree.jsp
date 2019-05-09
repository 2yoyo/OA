<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=request.getContextPath() +"/"%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<title>Insert title here</title>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="js/oa_utils.js"></script> 
</head>
<script type="text/javascript">
	
	<%
		String roleId = request.getParameter("param");
	%>
	
	$(function(){
		initDeptTree("/menuController/getParentMenuList");
	});
	
	
	function initDeptTree(url) {
		var setting = {
			check: {
					enable: true,
					chkStyle: "checkbox",
					chkboxType: { "Y": "ps", "N": "ps" }
			},
			data : {
				simpleData : {
					enable : true
				}
			}
		};
		
		debugger;
		$.get("authorizationController/getMenuzTree/<%=roleId%>","",function(data){   //data:简单json数据
			debugger
			//简单json数据转成标准json数据		
			var zNodes = formatJSON(data);
			debugger
			$.fn.zTree.init($("#QueryOrgTree"), setting,zNodes);
		},"JSON");
	}
	
	function formatJSON(json){
	    var ret = [], o = {};
		debugger;
	    function add(arr, data){
	        var obj = {
	            "id": data.id,
	            "pId": data.pId,
	            "name":data.name,
	            "type":data.menuType,
	            "open":true,
	            "children": [],
	            "checked":data.checked 
	        };
	        o[data.id] = obj;
	        arr.push(obj);
	    }
	 
	    json.forEach(x => {
	        if(o[x.pId]){
	            add(o[x.pId].children, x);
	        }else{
	            add(ret, x);
	        }
	    });
	    return ret;
	}
	
	
	function roleToMenu(){
		
		debugger;
		//获取角色id
		var roleId = <%=roleId%>;
		//获取菜单选中的id
		 var treeObj=$.fn.zTree.getZTreeObj("QueryOrgTree");
	     var nodes=treeObj.getCheckedNodes(true);
	     var menuIds = new Array();
		
	    //创建一个对象
	    var param = new Object();
	    
	    for(var i=0;i<nodes.length;i++){
	    	menuIds.push(nodes[i].id);
	    }
	 	
	    param.ids= menuIds;
	    param.roleId = roleId;
	    //异步提交数据
	    $.post("authorizationController/batchAdd",param,function(data){
	    	showMsg(data);
	    },"JSON")
	}

</script>
<body>
	<div id="QueryOrgTree" class="ztree"></div>
	
	<button onclick="roleToMenu()">授权</button>
</body>
</html>