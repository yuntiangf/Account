package com.cn.dao;

import java.util.ArrayList;
import java.util.List;

import com.cn.model.Tb_flag;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FlagDao {
	private DBHelper mDBHelper;
	private SQLiteDatabase db;
	
	public FlagDao(Context context){
		mDBHelper=new DBHelper(context);
	}
	
	//添加便签信息
	public void add(Tb_flag tb_flag){
		db=mDBHelper.getWritableDatabase();
		db.execSQL("insert into tb_flag (_id,flag) values(?,?)", new Object[]{tb_flag.get_id(),tb_flag.getFlag()});
	}
	
	//更新便签信息
	public void update(Tb_flag tb_flag){
		db=mDBHelper.getWritableDatabase();
		db.execSQL("update tb_flag set flag= ? where _id=?", new Object[]{tb_flag.getFlag(),tb_flag.get_id()});
	}
	
	//查找便签信息
	public Tb_flag find(int id){
		db=mDBHelper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select _id,flag from tb_flag where _id= ?", new String[]{String.valueOf(id)});
		if(cursor.moveToNext()){
			return new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")),
								cursor.getString(cursor.getColumnIndex("flag")));
		}
		return null;
	}
	
	//删除便签信息
	public void delete(Integer... ids){
		if(ids.length>0){
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < ids.length; i++) {
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length()-1);
			db=mDBHelper.getWritableDatabase();
			db.execSQL("delete from tb_flag where _id in("+sb+")", (Object[])ids);
		}
	}
	
	//获取便签信息
	public List<Tb_flag> getScrollData(int start,int count){
		List<Tb_flag> tb_flag=new ArrayList<Tb_flag>();
		db=mDBHelper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select * from tb_flag limit ?,?", 
						new String[]{String.valueOf(start),String.valueOf(count)});
		while(cursor.moveToNext()){
			tb_flag.add(new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")),
						cursor.getString(cursor.getColumnIndex("flag"))));
		}
		return tb_flag;
	}
	
	//获取总记录数
	public long getCount(){
		db=mDBHelper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select count(_id) from tb_flag", null);
		if(cursor.moveToNext()){
			return cursor.getLong(0);
		}
		return 0;
	}
	
	//获取最大编号
	public int getMaxId(){
		db=mDBHelper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select max(_id) from tb_flag", null);
		if(cursor.moveToLast()){
			return cursor.getInt(0);
		}
		return 0;
	}
}
