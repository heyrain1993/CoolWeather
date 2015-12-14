package com.coolweather.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.model.City;
import com.coolweather.model.Country;
import com.coolweather.model.Province;

/**
 * �������ݿ�
 * �ṩ���������ݴӶ����н������������浽���ݿ���
 * �ṩ������ѯʡ���У��ص�List����
 * @author Administrator
 *
 */
public class CoolWeatherDB {
	public static final String DB_NAME="cool_weather";//���ݿ������
	public static final int VERSION=1;//���ݿ�汾��
	private static CoolWeatherDB coolWeatherDB;//��������������õ���ģʽ
	private SQLiteDatabase db;
	
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper dbHelper=new CoolWeatherOpenHelper(context,DB_NAME,null, VERSION);
		db=dbHelper.getWritableDatabase();//�������ݿ�
	}
	
	/**
	 * ��Ϲ��췽�������õ���ģʽ
	 * @param context
	 * @return
	 */
	public synchronized static CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB==null){
			coolWeatherDB=new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}
	
	/**
	 * ��province���������ݱ����ʽ�洢����
	 * @param province
	 */
	public void saveProvince(Province province){
		if(province!=null){
			ContentValues values=new ContentValues();
			values.put("province_name",province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("province", null, values);
		}
	}
	
	/**
	 * ��ѯ���ݿ⽫����ʡ����Ϣ����
	 * @return
	 */
	public List<Province> loadProvince(){
		List<Province> list=new ArrayList<Province>();
		Cursor cursor=db.query("province",null,null,null,null,null,null);
		if(cursor.moveToFirst()){
			do{
				Province province=new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				list.add(province);
			}while(cursor.moveToNext());
		}
		if(cursor!=null){
			cursor.close();
		}
		return list;
	}
	
	/**
	 * ��city���������ݱ����ʽ�洢����
	 * @param city
	 */
	public void saveCity(City city){
		if(city!=null){
			ContentValues values=new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code",city.getCityCode());
			values.put("province_id",city.getProvince_id());
			db.insert("city", null, values);
		}
	}
	
	/**
	 * ��ѯ��Ӧʡ���е�������
	 * @return
	 */
	public List<City> loadCity(int provinceId){
		List<City> list=new ArrayList<City>();
		Cursor cursor=db.query("city", null,"province_id=?",new String[]{String.valueOf(provinceId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				City city=new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProvince_id(provinceId);
				list.add(city);
			}while(cursor.moveToNext());
		}
		if(cursor!=null){
			cursor.close();
		}
		return list;
	}
	/**
	 * ��country���������ݱ����ʽ�洢����
	 * @param country
	 */
	public void saveCountry(Country country){
		if(country!=null){
			ContentValues values=new ContentValues();
			values.put("country_name",country.getCountryName());
			values.put("country_code",country.getCountryCode());
			values.put("city_id", country.getCity_id());
			db.insert("country", null, values);
		}
	}
	
	/**
	 * ��ѯ��Ӧ�����е��س�
	 * @return
	 */
	public List<Country> loadCountry(int cityId){
		List<Country> list=new ArrayList<Country>();
		Cursor cursor=db.query("country",null,"city_id=?",new String[]{String.valueOf(cityId)},null,null,null);
		if(cursor.moveToFirst()){
			do{
				Country country=new Country();
				country.setId(cursor.getInt(cursor.getColumnIndex("id")));
				country.setCountryName(cursor.getString(cursor.getColumnIndex("country_name")));
				country.setCountryCode(cursor.getString(cursor.getColumnIndex("country_code")));
				country.setCity_id(cityId);
				list.add(country);
			}while(cursor.moveToNext());
		}
		if(cursor!=null){
			cursor.close();
		}
		return list;
	}
}
