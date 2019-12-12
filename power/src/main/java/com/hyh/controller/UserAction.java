package com.hyh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hyh.pojo.Module;
import com.hyh.pojo.Role;
import com.hyh.pojo.User;
import com.hyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserAction {
	@Autowired
	private UserService service;



	@RequestMapping("tologin")
	public String tologin(){
		return "login";
	}

	@RequestMapping("login")
	public String login(User user, HttpSession session){
		User u = service.login(user);
		//根据返回的数据确定账号是否正确
		if(u!=null){
			session.setAttribute("u", u);
			//根据用户获取角色
			Role role = service.findRoleById(u.getId());
			//通过session保护角色
			System.out.println(role);
			session.setAttribute("role", role);
			return "ifream";
			
		}
		return "login";
	}
	//查询权限列表
	@RequestMapping("getModuleList")
	@ResponseBody
	public List<Module> getModuleList(HttpSession session){
		Role role = (Role) session.getAttribute("role");
		System.out.println("role"+role.getRid());
		//获取角色的权限
		List<Module> lists = service.getModuleList(role.getRid());
		for (Module m : lists) {
			System.out.println(m.getMid()+m.getModuleName());
		}
		
		return lists;
	}
	
	//列表
	@RequestMapping("list")
	public String list(User user,Model model){
		List<User> lists = service.findUserList();
		System.out.println(lists);
		model.addAttribute("lists", lists);
		return "list";
	}
	@RequestMapping("delete")
	//删除
	public String delete(int id){
		service.delete(id);
		return "redirect:list";
	}
	//追加职位
	@RequestMapping("findRoleList")
	@ResponseBody
	public List<Role> findRoleList(){
		List<Role> rlist= service.findRoleList();
		System.out.println(rlist);
		return rlist;
	}
	//添加
	@RequestMapping("add")
	public String add(User user){
		String[] rids = user.getRids().split(",");
		for (String rid : rids) {
			System.out.println(rid);
			user.getRidList().add(rid);
		}
		service.add(user);
		return "redirect:list";
		
	}
	
}
