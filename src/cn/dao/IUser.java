package cn.dao;

import java.util.ArrayList;

import cn.domain.User;

public interface IUser {
	//登录
	User login(String name, String password);
	//删除
	boolean delete(int ids);
	//获取所有数据
	ArrayList<User> getlist();
	//修改
	boolean update(User u);
	//添加
	boolean inset(User u);

}
