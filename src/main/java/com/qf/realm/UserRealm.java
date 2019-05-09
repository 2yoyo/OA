package com.qf.realm;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.qf.entity.Emp;
import com.qf.service.IEmpService;
import com.qf.service.IMenuService;
import com.qf.service.impl.EmpServiceImpl;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private IEmpService empService;
	
	@Autowired
	private IMenuService menuService;
	
	/**
	 * 获取用户授权的信息方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("用户授权调用");
		
		// 1.获取当前登陆的用户
		Emp emp = (Emp)SecurityUtils.getSubject().getPrincipal();
		
		// 2.根据用户userId查询用户权限
		Set<String> set =  menuService.getPerCodeByEmpId(emp.getId());
		
		SimpleAuthorizationInfo saz = new SimpleAuthorizationInfo();
		saz.addStringPermissions(set);
		
		return saz;
	}

	/**
	 * 获取用户认证信息的方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("用户认证调用");
		
		// 1.根据用户名查询对象
		String username = (String)token.getPrincipal();
		Emp emp = empService.getEmpByUsername(username);
		
		// 2.封装
		SimpleAuthenticationInfo sati = new SimpleAuthenticationInfo(emp, emp.getPassword(), ByteSource.Util.bytes(username), getName());
			
//		SecurityUtils.getSubject().getPrincipal(); // 
		
		// 3.返回，shiro做密码的比对
		return sati;
	}
	
	/**
	 * 清空用户的缓存
	 */
	public void clearCache(){
		 PrincipalCollection principal = SecurityUtils.getSubject().getPreviousPrincipals();
		super.clearCache(principal); 
	}
}
