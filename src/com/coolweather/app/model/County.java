package com.coolweather.app.model;

/**
 * county的实体类，方便对county的操作
 * @author 林调
 * @time 2016.12.28
 */

public class County {
	private int id;
    private String countyName;
    private String countyCode;
    private int cityId;
    
    public int getId() {
		return id;
	}
     
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCountyName() {
		return countyName;
	}
	
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
	public String getCountyCode() {
		return countyCode;
	}
	
	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	
	public int getCityId() {
		return cityId;
	}
	
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
		
}
