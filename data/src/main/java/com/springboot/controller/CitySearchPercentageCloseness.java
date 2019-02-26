package com.springboot.controller;

import java.util.ArrayList;

public class CitySearchPercentageCloseness implements Search{
	private static final double NAME_LENGTH_TREHSOLD = 0.5;
	
	public CitySearchPercentageCloseness() {}
	
	public ArrayList<City> search(ArrayList<City> cities,String cityToSearch){
		ArrayList<City> potentialCityList = new ArrayList<City>();
		double percentNameMatch = 0.0;
		
		//Search in all cities for names that matches at least 50% of cityToSearch name length
		for(City city : cities) {
			if (city.getCityNameOnly().toLowerCase().contains(cityToSearch.toLowerCase())){
				percentNameMatch = ((double)cityToSearch.length() / (double)city.getCityNameOnly().length());
				if (percentNameMatch >= NAME_LENGTH_TREHSOLD) {
					potentialCityList.add(city); 
				}
			}
		}
		return potentialCityList;		
	}
	
}