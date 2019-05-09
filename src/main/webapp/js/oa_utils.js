function selectParentDept(url) {

	// 1.打开一个框
	layer.open({
		type : 1,
		title : false,
		closeBtn : 2,
		shadeClose : true,
		skin : 'yourclass',
		content : getHtml()
	});

	//2.初始化树插件
	initDeptTree(url);
}

function initDeptTree(url) {
	debugger
	var setting = {
		data : {
			simpleData : {
				enable : true
			//使用简单 Array格式的数据 
			}
		},
		async : {
			enable : true, //开启异步加载处理   
			url : url,
			autoParam : [ "id" ],
			dataType : "json", //默认text  
			type : "get" //默认post  
		},
		callback : {
			
			onClick : ClickQueryOrgTreeNodeFunc
		// 点击节点的时候调用
		}
	};
	$.fn.zTree.init($("#QueryOrgTree"), setting);
}

function getHtml() {
	var htmlStr = "";
	htmlStr += '<div style="width:300px;height:200px" >';
	htmlStr += '<div style=" line-height:30px; text-indent:10px; margin-bottom:20px; background-color:#eee; position:relative;">组织</div>';
	htmlStr += '<div style="display:block; padding-bottom:5px;" align="center"  >';
	htmlStr += '<div><table><tr><td><input type="hidden" id="tmp_deptId" width="50px"/><input type="text" id="tmp_deptName" width="50px"><button onclick="SetQueryDeptTreeParam();">确定</button></td></tr></table></div>';
	htmlStr += '<div id="QueryOrgTree" class="ztree"></div>';
	htmlStr += '</div>';
	htmlStr += '</div>';
	return htmlStr;
}

//用户选择的部门和部门id放到临时地
function ClickQueryOrgTreeNodeFunc(event, treeId, treeNode, clickFlag) {
	debugger
	$("#tmp_deptId").val(treeNode.id);
	$("#tmp_deptName").val(treeNode.name);
}

//将选中的部门和部门id放入表单中
function SetQueryDeptTreeParam() {
	$("#dparentname").val($("#tmp_deptName").val());
	$("#dparentid").val($("#tmp_deptId").val());
	layer.close(layer.index);
}

//提交表单数据
function submit(url,form){
	debugger;
	var param=formToObject(form)
	
	$.post(url,param,function(data){
		showMsg(data);
	},"JSON");
}

//把form对象转成param
function formToObject(form){
	debugger
	var param = {};
	
	//获取表单参数
	var jsonArray = form.serializeArray();
	
	//把jsonArray中的数据放入param对象中
	for(var i = 0;i<jsonArray.length;i++){
		param[jsonArray[i].name] = jsonArray[i].value;
	}
	return param;
}

function showMsg(data){
	if(data.flag){
		debugger;
		layer.msg(data.message,{icon:1,time:1000},function(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
		
		location.reload();
		});
	}else{
		layer.msg(data.message,{icon:2,time:1000});
	}
}

