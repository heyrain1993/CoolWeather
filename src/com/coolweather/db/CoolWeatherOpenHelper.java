package com.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建建表语句
 * @author Administrator
 *
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper{
	//建表语句
	private static final String create_province="create table province("
			+"id integer primary key autoincrement,"
			+"province_name text,"
			+"province_code text)";
	private static final String create_city="create table city("
			+"id integer primary key autoincrement,"
			+"city_name text,"
			+"city_code text,"
			+"province_id integer)";
	private static final String create_country="create table country("
			+"id integer primary key autoincrement,"
			+"city_name text,"
			+"city_code text,"
			+"city_id integer)";
	
	/**
	 * 父类的构造方法，调用此方法创建数据库
	 * @param context：上下文对象
	 * @param name：数据库的名字
	 * @param factory：
	 * @param version：数据库的版本号
	 */
	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}
	
	/**
	 * 通常在此方法中编写创建表的逻辑代码
	 * 在创建SQLiteOpenHelper对象后，如果对象调用
	 * getReadableDatabase方法创建数据库时，系统自动调用此方法创建表
	 * 只有在数据库第一次创建时自动调用此方法
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(create_province);//创建省份表
		db.execSQL(create_city);//创建城市表
		db.execSQL(create_country);//创建县城表
	}
	
	/**
	 * 当数据库的版本号发生变化时调用此方法更新
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
