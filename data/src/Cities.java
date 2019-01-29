package com.mad.covo_challenge;

import java.util.ArrayList;
import java.util.Collections;

public class Cities {

	public ArrayList<City> suggestion = new ArrayList<City>();
	
	public Cities(String name, double latitude, double longitude) {
		CityRepository cityRepo = new CityRepository(); //Construct list of cities from file of cities. See constructor for more info
		
		suggestion = cityRepo.citySearch(name,latitude,longitude); //Search 
		Collections.sort(suggestion, new CityCompareScore()); //Sort by score
	}
	
	public ArrayList<City> getSuggestion(){
		return suggestion;
	}
}
