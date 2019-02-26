package com.springboot.controller;

import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class City{
	private String cityName;
	private String name;
	private String latitude;
	private String longitude;
	private double score;
	
	public City(String name, String provinceOrState, String country, String latitude, String longitude) {
		this.cityName 	= name;
		this.name 		= name + ", " + provinceOrState + ", " + country;
		this.latitude 	= latitude;
		this.longitude 	= longitude;
		score 			= 0.0;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String lat) {
		this.latitude = lat;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	@JsonIgnore
	public String getCityNameOnly(){ 
		return cityName;
	}
	
	public String getName() {
		return name;
	}
	
}

//Sort by decreasing score
class CityCompareScore implements Comparator<City>{
	public int compare(City c1, City c2) {
		if (c1.getScore() < c2.getScore()) {
			return 1;
		}else {
			return -1;
		}
	}
}

//Sort by alphabetic order
class CityCompareName implements Comparator<City>{
	public int compare(City c1, City c2) {
		return c1.getName().compareTo(c2.getName());
	}
}

