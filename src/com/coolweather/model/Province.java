package com.coolweather.model;

/**
 * 实体类：全国的省份
 * @author Administrator
 *
 */
public class Province {
	private int id;//表示省份表的主键
	private String provinceName;//表示省份的名字
	private String provinceCode;//表示省份的代号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	
}
