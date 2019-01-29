package com.mad.covo_challenge;



import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

public class ChallengeResourceTest  {
	CityRepository cityRepo = new CityRepository();


	@Test
	public void testCitySearchNotInList() {
		String testName 		= "Tatooin";
		
		ArrayList<City> citiesFound = new ArrayList<City>(); 
		citiesFound = cityRepo.citySearch(testName, 0, 0);
		
		int foundCity = citiesFound.size();
		int expected = 0;
		assertEquals(expected, foundCity);
		
	}
	
	@Test
	public void testCitySearchNameOnly() {
		String testName 		= "Roberval";
		double expectedScore 	= 1;
		
		ArrayList<City> citiesFound = new ArrayList<City>(); 
		citiesFound = cityRepo.citySearch(testName, 0, 0);
		
		String foundCityName = citiesFound.get(0).returnCityNameOnly();
		double foundScore = citiesFound.get(0).getScore();
		
		assertEquals(testName, 		foundCityName);
		assertEquals(expectedScore, foundScore ,0);
		
	}
	
	@Test
	public void testCitySearchNameLatitudeAndLongitude() {
		String testName 		= "Roberval";
		double testLatitude 	= 40.5168;
		double testLongitude 	= -70.23244;
		double expectedScore 	= 0.56; //Hardcoded
		
		ArrayList<City> citiesFound = new ArrayList<City>(); 
		citiesFound = cityRepo.citySearch(testName, testLatitude, testLongitude);
		
		String foundCityName = citiesFound.get(0).returnCityNameOnly();
		double foundScore = citiesFound.get(0).getScore();	
		
		assertEquals(testName, 		foundCityName);
		assertEquals(expectedScore, foundScore ,0);
	}
    
	@Test
	public void testCitySearchNameLatitudeAndLongitudeList() {
		//Will return a list of 2 cities
		String testName 		= "Sydney";
		double testLatitude 	= 46.5;
		double testLongitude 	= -60.2;
		
		ArrayList<City> citiesFound = new ArrayList<City>(); 
		citiesFound = cityRepo.citySearch(testName, testLatitude, testLongitude);

		
		String expectedCity1	= testName + ", NS, CA";
		String expectedCity2	= testName + " Mines, NS, CA";
		double expectedScore1 		= 0.97;		//Hardcoded
		double expectedScore2 		= 0.79;		//Hardcoded	

		String foundCityName1 = citiesFound.get(0).getName();
		double foundScore1 = citiesFound.get(0).getScore();	
		
		String foundCityName2 = citiesFound.get(1).getName();
		double foundScore2 = citiesFound.get(1).getScore();	
		
		assertEquals(expectedCity1, 	foundCityName1);
		assertEquals(expectedScore1, 	foundScore1 ,0);
		
		assertEquals(expectedCity2,		foundCityName2);
		assertEquals(expectedScore2, 	foundScore2 ,0);
		
	}
	
}