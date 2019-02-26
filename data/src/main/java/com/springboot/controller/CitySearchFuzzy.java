package com.springboot.controller;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import java.util.ArrayList;

//From Fuzzy Search library : https://github.com/xdrop/fuzzywuzzy/blob/master/README.md

public class CitySearchFuzzy implements Search{
	private static final double TRESHSOLD = 100;
	public CitySearchFuzzy() {};
	
	public ArrayList<City> search(ArrayList<City> cities, String cityToSearch){
		ArrayList<City> potentialCityList = new ArrayList<City>();

		for(City city : cities) {
			if (FuzzySearch.partialRatio(cityToSearch.toLowerCase(), city.getCityNameOnly().toLowerCase() ) == TRESHSOLD){
					potentialCityList.add(city); 
			}
		}
		return potentialCityList;		
	}
	
}
