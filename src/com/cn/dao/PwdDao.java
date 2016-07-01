package com.cn.dao;

import com.cn.model.Tb_pwd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PwdDao {
	private DBHelper mDBhelper;
	private SQLiteDatabase db;
	
	public PwdDao(Context context){
		mDBhelper=new DBHelper(context);// 初始化DBOpenHelper对象
		
	}
	
	//添加密码信息
	public void add(Tb_pwd tb_pwd){
		db=mDBhelper.getWritableDatabase();
		db.execSQL("insert into tb_pwd(password) values(?)", new Object[] {tb_pwd.getPassword()} );
	}
	
	//设置密码信息
	public void update(Tb_pwd tb_pwd){
		db=mDBhelper.getWritableDatabase();
		db.execSQL("update tb_pwd set password=? ", new Object[] {tb_pwd.getPassword()} );
	}
	
	//查找密码信息
	public Tb_pwd find(){
		db=mDBhelper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select password from tb_pwd", null);
		if(cursor.moveToNext()){
			Tb_pwd password=new Tb_pwd(cursor.getString(cursor.getColumnIndex("password")));
			return password;
			
		}
		return null;
	}
	
	//获取密码总记录数
	public long getCount(){
		db=mDBhelper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select count(password) from tb_pwd", null);
		if(cursor.moveToNext()){
			return cursor.getLong(0);
		}
		return 0;
	}
}
