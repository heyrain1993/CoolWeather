package com.coolweather.model;

/**
 * ʵ���ࣺ��ʾ���ڵ��س�
 * @author Administrator
 *
 */
public class Country {
	private int id;//��ʾ�سǵ�ID
	private String countryName;//��ʾ�سǵ�����
	private String countryCode;//��ʾ�سǵĴ���
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
