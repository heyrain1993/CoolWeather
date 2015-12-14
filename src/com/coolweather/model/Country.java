package com.coolweather.model;

/**
 * 实体类：表示市内的县城
 * @author Administrator
 *
 */
public class Country {
	private int id;//表示县城的ID
	private String countryName;//表示县城的名字
	private String countryCode;//表示县城的代号
	private int city_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	
}
