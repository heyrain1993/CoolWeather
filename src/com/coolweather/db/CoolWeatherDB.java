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
 * 创建数据库
 * 提供方法将数据从对象中解析出来，保存到数据库中
 * 提供方法查询省，市，县的List集合
 * @author Administrator
 *
 */
public class CoolWeatherDB {
	public static final String DB_NAME="cool_weather";//数据库的名称
	public static final int VERSION=1;//数据库版本号
	private static CoolWeatherDB coolWeatherDB;//本类变量用于设置单例模式
	private SQLiteDatabase db;
	
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper dbHelper=new CoolWeatherOpenHelper(context,DB_NAME,null, VERSION);
		db=dbHelper.getWritableDatabase();//创建数据库
	}
	
	/**
	 * 配合构造方法，设置单例模式
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
	 * 将province对象以数据表的形式存储起来
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
	 * 查询数据库将所有省份信息返回
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
	 * 将city对象以数据表的形式存储起来
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
	 * 查询对应省份中的所有市
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
	 * 将country对象以数据表的形式存储起来
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
	 * 查询对应市所有的县城
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
