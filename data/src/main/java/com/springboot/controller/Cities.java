package com.springboot.controller;

import java.util.ArrayList;

public class Cities {

	public ArrayList<City> suggestion = new ArrayList<City>();
	
	public Cities(String name, double latitude, double longitude) {
		CityRepository cityRepo = new CityRepository(name,latitude,longitude);
	
		suggestion = cityRepo.getSuggestions();
	}
	
	public ArrayList<City> getSuggestion(){
		return suggestion;
	}
}
