package com.hyh.service;

import java.util.List;

import com.hyh.mapper.UserMapper;
import com.hyh.pojo.Module;
import com.hyh.pojo.Role;
import com.hyh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {
	@Autowired
	private UserMapper mapper;


	//登录
	public User login(User user) {
		// TODO Auto-generated method stub
		return mapper.login(user);
	}
	//根据用户获取角色
	public Role findRoleById(int id) {
		// TODO Auto-generated method stub
		return mapper.findRoleById(id);
	}
	//查询权限列表
	public List<Module> getModuleList(int rid) {
		// TODO Auto-generated method stub
		return mapper.getModuleList(rid);
	}
	//列表
	public List<User> findUserList() {
		// TODO Auto-generated method stub
		return mapper.findUserList();
	}
	//删除
	public void delete(int id) {
		// TODO Auto-generated method stub
		mapper.deletd(id);
	}
	//追加
	public List<Role> findRoleList() {
		// TODO Auto-generated method stub
		return mapper.findRoleList();
	}
	//添加
	public void add(User user) {
		// TODO Auto-generated method stub
		mapper.add(user);
	}
	
	
}
