package com.springboot.controller;

import java.util.ArrayList;


public class CitySearchKMP implements Search{

	public CitySearchKMP() {}
	
	
	public ArrayList<City> search(ArrayList<City> cities, String name){
		KMPStringMatching kmpSearch = new KMPStringMatching();
		
		ArrayList<City> potentialCityList = new ArrayList<City>();
		
		for(City city : cities) {
			if (kmpSearch.KMPSearch(name.toLowerCase(), city.getCityNameOnly().toLowerCase())) {
					potentialCityList.add(city); 
			}	
			
		}
		return potentialCityList;		
	}
}
