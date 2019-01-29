package com.mad.covo_challenge;

import java.util.Comparator;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"name","latitude","longitude","score"})
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
	
	//hack : JAX-RS call this if it's named with a get prefix like getCityNameOnly. renamed to returnCity.. 
	//Proper way would be to find how jersey hide methods or switch to jackson that seems to have this capability. Jersey doesn't seem obvious. To fix later.
	public String returnCityNameOnly(){ 
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

