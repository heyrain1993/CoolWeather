package com.coolweather.model;

/**
 * ʵ���ࣺ��ʾʡ���ڵ���
 * @author Administrator
 *
 */
public class City {
	private int id;//�ڳ��б��е�ID
	private String cityName;//��ʾ���е�����
	private String cityCode;//��ʾ���еĴ���
	private int province_id;
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
	public int getProvince_id() {
		return province_id;
	}
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	
}
