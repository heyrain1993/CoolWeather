package com.coolweather.model;

/**
 * 实体类：表示省份内的市
 * @author Administrator
 *
 */
public class City {
	private int id;//在城市表中的ID
	private String cityName;//表示城市的名字
	private String cityCode;//表示城市的代号
	private int provinceId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	
}
