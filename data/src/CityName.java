package com.mad.covo_challenge;

public class CityName {
	private String name;
	private String provinceOrState;
	private String country;
	
	public CityName(String name, String provinceOrState, String country) {
		this.name = name;
		this.provinceOrState = provinceOrState;
		this.country = country;
		
	}

	public String getName() {
		return name + ", " + provinceOrState + ", " + country;
		
	}

	public String getCityNameOnly() {
		return name;
	}
	
}
