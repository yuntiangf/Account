package com.cn.model;

//密码数据表实体类
public class Tb_pwd {
	private String password;

	//无参构造函数
	public Tb_pwd() {
		super();  
	}
	//有参构造函数
	public Tb_pwd(String password) {
		super();
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
