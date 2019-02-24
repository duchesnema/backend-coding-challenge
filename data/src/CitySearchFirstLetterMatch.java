package com.springboot.controller;

import java.util.ArrayList;

public class CitySearchFirstLetterMatch implements Search{

	public CitySearchFirstLetterMatch() {};
	
	public ArrayList<City> search(ArrayList<City> cities, String cityToSearch){
		ArrayList<City> potentialCityList = new ArrayList<City>();

		for(City city : cities) {
			if (city.getCityNameOnly().toLowerCase().contains(cityToSearch.toLowerCase())){
				if (city.getCityNameOnly().startsWith(cityToSearch)) {
					potentialCityList.add(city); 
				}
			}
		}
		return potentialCityList;		
	}
	
}