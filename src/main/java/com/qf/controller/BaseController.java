package com.qf.controller;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qf.entity.Page;
import com.qf.service.IBaseService;

public abstract class BaseController<T> {
	
	private Gson gson = new Gson();

	private Class<T> enttiyClasses; //实体类的class对象
	
	private String entiyName; // 实体类名首字母小写

	private String namesapce; // 子类上@RequestMapping中value的属性值
	
	@SuppressWarnings(value="all") // 压制警告
	public BaseController() {
		// 1.返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
		ParameterizedType types = (ParameterizedType) this.getClass().getGenericSuperclass();

		// 2.返回表示此类型实际类型参数的 Type 对象的数组。
		Type[] actualTypeArguments = types.getActualTypeArguments();

		// 3.从数组中获取对对应的类型
		enttiyClasses = (Class<T>) actualTypeArguments[0];
		
		// 4.把类名转小写
		entiyName = enttiyClasses.getSimpleName().toLowerCase();
		
		// 6.先获取@RequestMapping注解
		RequestMapping requestMapping = this.getClass().getAnnotation(RequestMapping.class);
		
		// 7.给namespace赋值
		namesapce = requestMapping.value()[0];
	}

	protected abstract IBaseService<T> getBasService();

	@RequestMapping(value = "/getPage")
	public String getPage(T t,Page<T> page, ModelMap map) {

		// 1.封装分页对象
		PageInfo<T> pageInfo = getBasService().getPage(page,t);

		// 2.把pageInfo放到Map中
		map.put("page", pageInfo);
		map.put("url", getPageUrl(t)); // ?

		// 3.跳转到视图页面
		return entiyName+"/"+entiyName+"List"; // User,userList.jsp,userAdd.jsp,userUdpate.jsp
	}

	@RequestMapping(value="/add",produces="text/html;charset=utf8")
	@ResponseBody
	public String add(T t){
		int add = getBasService().add(t);
		return responceClient(add);
	}
	
	@RequestMapping(value = "/getById/{id}")
	public String getById(@PathVariable Integer id,ModelMap map) {
		T enttiy = getBasService().getById(id);
		map.put(entiyName, enttiy); // map.put("user",entity);
		return entiyName+"/"+entiyName+"Update";
	}
	
	@RequestMapping(value="/update",produces="text/html;charset=utf8")
	@ResponseBody
	public String  update(T t){
		int add = getBasService().update(t);
		return responceClient(add);
	}
	
	public String responceClient(int flag){
		Map<String, Object> map = new HashMap<String,Object>();
		
		if(flag>0){
			map.put("flag", true);
			map.put("message", "操作成功");
		}else{
			map.put("flag", false);
			map.put("message", "操作失败");
		}
		return toJson(map);
	}
	
	
	@RequestMapping(value = "/delete/{id}",produces="text/html;charset=utf8")
	@ResponseBody
	public String delete(@PathVariable Integer id) {
		int delete = getBasService().delete(id);
		return responceClient(delete);
	}
	
	public String toJson(Object obj){
		return gson.toJson(obj);
	}
	
	@RequestMapping(value="/batchDel",produces="text/html;charset=utf8")
	@ResponseBody
	public String batchDel(@RequestParam("ids[]")Integer[] ids){
		int batchDel = getBasService().batchDel(ids,"t_dept");
		return responceClient(batchDel);
	}
	
	public String getPageUrl(T t){
		StringBuffer buffer = new StringBuffer(namesapce+"/getPage?");
		Field[] dFields = t.getClass().getDeclaredFields();	
		for(int i=0;i<dFields.length;i++){
			Field field = dFields[i];
			field.setAccessible(true);
			try {
				//获取属性
				Object value = field.get(t);
				
				if(value != null){
					buffer.append(field.getName()+"="+value+"&");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}
}
