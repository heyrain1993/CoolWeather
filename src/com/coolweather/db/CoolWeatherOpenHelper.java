package com.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * �����������
 * @author Administrator
 *
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper{
	//�������
	private static final String create_province="create table Province("
			+"id integer primary key autoincrement,"
			+"province_name text,"
			+"province_code text)";
	private static final String create_city="create table City("
			+"id integer primary key autoincrement,"
			+"city_name text,"
			+"city_code text,"
			+"province_id integer)";
	private static final String create_country="create table Country("
			+"id integer primary key autoincrement,"
			+"country_name text,"
			+"country_code text,"
			+"city_id integet)";
	
	/**
	 * ����Ĺ��췽�������ô˷����������ݿ�
	 * @param context�������Ķ���
	 * @param name�����ݿ������
	 * @param factory��
	 * @param version�����ݿ�İ汾��
	 */
	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}
	
	/**
	 * ͨ���ڴ˷����б�д��������߼�����
	 * �ڴ���SQLiteOpenHelper���������������
	 * getReadableDatabase�����������ݿ�ʱ��ϵͳ�Զ����ô˷���������
	 * ֻ�������ݿ��һ�δ���ʱ�Զ����ô˷���
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(create_province);//����ʡ�ݱ�
		db.execSQL(create_city);//�������б�
		db.execSQL(create_country);
	}
	
	/**
	 * �����ݿ�İ汾�ŷ����仯ʱ���ô˷�������
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
