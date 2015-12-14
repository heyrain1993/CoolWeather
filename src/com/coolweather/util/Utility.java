package com.coolweather.util;

import android.text.TextUtils;

import com.coolweather.db.CoolWeatherDB;
import com.coolweather.model.City;
import com.coolweather.model.Country;
import com.coolweather.model.Province;

public class Utility {
	/**
	 * 解析服务器返回的省级数据
	 * @param coolWeatherDB
	 * @param response
	 * @return
	 */
	public synchronized static boolean handleProvinceResponse(CoolWeatherDB coolWeatherDB,String response){
		if(!TextUtils.isEmpty(response)){
			String[] allProvince=response.split(",");
			if(allProvince!=null&&allProvince.length>0){
				for(String p:allProvince){
					String[] array=p.split("\\|");
					Province province=new Province();
					province.setProvinceName(array[0]);
					province.setProvinceCode(array[1]);
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 将数据保存到数据库
	 * @param coolWeatherDB
	 * @param response
	 * @param provinceId
	 * @return
	 */
	public synchronized static boolean handleCityResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
		if(!TextUtils.isEmpty(response)){
			String[] allCity=response.split(",");
			if(allCity!=null&&allCity.length>0){
				for(String c:allCity){
					String[] array=c.split("\\|");
					City city=new City();
					city.setCityName(array[0]);
					city.setCityCode(array[1]);
					city.setProvince_id(provinceId);
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 解析从服务器返回的数据，并将它们存储到数据库
	 * @param coolWeatherDB
	 * @param response
	 * @param cityId
	 * @return
	 */
	public synchronized static boolean handleCountryResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
		if(!TextUtils.isEmpty(response)){
			String[] allCountry=response.split(",");
			if(allCountry!=null&&allCountry.length>0){
				for(String c:allCountry){
					String[] array=c.split("\\|");
					Country country=new Country();
					country.setCountryName(array[0]);
					country.setCountryCode(array[1]);
					country.setCity_id(cityId);
					coolWeatherDB.saveCountry(country);
				}
				return true;
			}
		}
		return false;
	}
}
