package com.cn.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static final int VERSION = 1;
	private static final String DBNAME="account.db";
	
	public DBHelper(Context context) {
		super(context,DBNAME, null, VERSION);	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table tb_outaccount(_id integer primary key,money decimal,time varchar(10),"
				+ "type varchar(10),address varchar(100),mark varchar(200))");//֧�����ݱ�
		db.execSQL("create table tb_inaccount(_id integer primary key,money decimal,time varchar(10),"
				+ "type varchar(10),handler varchar(100),mark varchar(200))");//�������ݱ�
		db.execSQL("create table tb_flag(_id integer primary key,flag varchar(200))");//��ǩ��Ϣ��
		db.execSQL("create table tb_pwd(password varchar(200))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
