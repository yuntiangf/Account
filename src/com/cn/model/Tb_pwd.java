package com.cn.model;

//�������ݱ�ʵ����
public class Tb_pwd {
	private String password;

	//�޲ι��캯��
	public Tb_pwd() {
		super();  
	}
	//�вι��캯��
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
