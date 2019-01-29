package com.mad.covo_challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class CityRepository {
	
	private static final String FILE_PATH = "/Users/mad/eclipse-workspace-2018-12/covo-challenge/src/main/java/com/mad/covo_challenge/cities_canada-usa.tsv";
	private static final String FILE_PATH_GITHUB = "https://raw.githubusercontent.com/duchesnema/backend-coding-challenge/master/data/cities_canada-usa.tsv";
	
	private static final double COEFF_SCORE_CITY_ONLY = 0.8;
	private static final double WEIGHT_SCORE_CITY_NAME_MATCHES = 0.25;
	private static final double WEIGHT_SCORE_CITY_LAT_LONG = 0.75;
	private static final double COEFF_SCORE_CITY = 0.9;
	
	private ArrayList<City> cities = new ArrayList<City>();
	String toto = "https://raw.githubusercontent.com/Crunchify/All-in-One-Webmaster/master/all-in-one-webmaster-premium.php";
	
	
	
	public CityRepository() {
		try {
			readCityList(cities); //Read cities from the file
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<City> citySearch(String cityToSearch, double latitude, double longtitude) {
		ArrayList<City> potentialCityList = new ArrayList<City>();

		//Search in all cities and if city to search matches the name of a city and this city start with same letter 
		//this is to avoid London and New London, for alphabetical search. Then build a potential city list to return
		for(City c : cities) {
			if (c.returnCityNameOnly().contains(cityToSearch)){
				if ( (c.returnCityNameOnly().charAt(0) == cityToSearch.charAt(0)) && (c.returnCityNameOnly().contains(cityToSearch)) ) {
					potentialCityList.add(c); 
				}
			}
		}
		
		giveScoreToCities(potentialCityList,cityToSearch,latitude,longtitude); //Give a score to best matching city
		
		return potentialCityList;
	}
	
	private ArrayList<City> giveScoreToCities(ArrayList<City> potentialCityList, String cityToSearch, double latitude, double longitude){
		//Algo to score. 
		//If name matches 100% and no latitude and longitude is passed in argument, score is set to 1.
		//If name doesn't match 100% and no latitude,longitude is passed in argument, score is given by a % of extra letters
		//WEIGHT_SCORE_CITY_ONLY power howManyLettersMore is used. following a graph similar to y=0.8^x, where x is nb of extra letters. 
		
		//If lat and long are used, distance from the city to search is calculated from each city found 
		//and a weight is applied in a ratio of x% to distance y% to name match
		
		setScoreWithNameMatch(potentialCityList,cityToSearch);
		if (latitude != 0.0 && longitude != 0.0) {
			setScoreWithLongNLat(potentialCityList,cityToSearch,latitude,longitude);	
		}
		return potentialCityList;
	}
	
	private void setScoreWithLongNLat(ArrayList<City> potentialCityList, String cityToSearch, double latitude, double longitude) { 
		
		//Adjust score according to distance
		for (int i = 0; i < potentialCityList.size(); i++) {
			double scoreForNameMatch = potentialCityList.get(i).getScore();

			double y1 = Double.parseDouble(potentialCityList.get(i).getLatitude());
			double x1 = Double.parseDouble(potentialCityList.get(i).getLongitude());
			double distanceFromOrigin = MathUtils.distanceCity(y1, x1, latitude, longitude);
			
			double scoreFromDistance = Math.pow(COEFF_SCORE_CITY,distanceFromOrigin);
			double finalScore = scoreForNameMatch * WEIGHT_SCORE_CITY_NAME_MATCHES + scoreFromDistance * WEIGHT_SCORE_CITY_LAT_LONG;
		
			potentialCityList.get(i).setScore(MathUtils.round(finalScore,2));
		}
	}

	private void setScoreWithNameMatch(ArrayList<City> potentialCityList, String cityToSearch) {
		for (int i = 0; i < potentialCityList.size(); i++) {
			if (potentialCityList.get(i).returnCityNameOnly().length() == cityToSearch.length()) { //getName returns a string with name, province/state, country. Split to get only the name
				potentialCityList.get(i).setScore(1.0);
			}else {
				int howManyLettersMore = potentialCityList.get(i).returnCityNameOnly().length() - cityToSearch.length();
				potentialCityList.get(i).setScore(MathUtils.round(Math.pow(COEFF_SCORE_CITY_ONLY,howManyLettersMore),2));
			}
		}		
	}
	
	private void readCityList(ArrayList<City> cities) throws IOException {
		BufferedReader reader = null;
		
		try {
			URL url = new URL(FILE_PATH_GITHUB);
			
			HttpURLConnection githubHttp = (HttpURLConnection) url.openConnection();
			if (githubHttp.getResponseCode() == 200) {
				InputStream inputStream = githubHttp.getInputStream();
				reader = new BufferedReader(new InputStreamReader(inputStream));
			}
			//reader = new BufferedReader(new FileReader(FILE_PATH));
			
			String line = reader.readLine(); // Read first line.
			int lineCnt 	= 1;
			int nameIndex	= 0;
			int latIndex	= 0;
			int longIndex	= 0;
			int admin1Index	= 0;
			int countryIndex= 0;
			String country;
			String longitude;
			String latitude;
			String cityName;
			String provinceOrState;
			
			while(line != null) {
				String[] splited = line.split("\\t");  //Split line at tab boundaries

				if (lineCnt==1) { // On first line only, check the indexes of each field and store them. They are useful to create our city array according to name,lat,long
					for(int i = 0; i < splited.length; i++) {
						if      (splited[i].equals("name")){
							nameIndex 		= i;
						}
						else if (splited[i].equals("lat")){
							latIndex 		= i;
						}
						else if (splited[i].equals("long")){
							longIndex 		= i;
						}
						else if (splited[i].equals("admin1")){
							admin1Index 	= i;
						}
						else if (splited[i].equals("country")){
							countryIndex 	= i;
						}
					}
				}else {
					country			= splited[countryIndex];
					longitude		= splited[longIndex];
					latitude		= splited[latIndex];
					cityName		= splited[nameIndex];
					provinceOrState = splited[admin1Index];
					
					if (country.equals("CA")) {
						provinceOrState = getCanadianProvinceAbvr(provinceOrState);
					}
					cities.add(new City(cityName, provinceOrState, country, latitude, longitude));
				}
				
				line = reader.readLine();
				lineCnt ++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("There was a problem reading city file : " + e);
			e.printStackTrace();
		}		
	}
	
	private String getCanadianProvinceAbvr(String str) {
		String returnCode = "";
		if      (str.equals("01")) {
			returnCode = "AB";
		}
		else if (str.equals("02")) {
			returnCode = "BC";
		}
		else if (str.equals("03")) {
			returnCode = "MB";
		}
		else if (str.equals("04")) {
			returnCode = "NB";
		}
		else if (str.equals("05")) {
			returnCode = "NL";
		}
		else if (str.equals("07")) {
			returnCode = "NS";
		}
		else if (str.equals("08")) {
			returnCode = "ON";
		}
		else if (str.equals("09")) {
			returnCode = "PE";
		}
		else if (str.equals("10")) {
			returnCode = "QC";
		}
		else if (str.equals("11")) {
			returnCode = "SK";
		}
		else if (str.equals("12")) {
			returnCode = "YT";
		}
		else if (str.equals("13")) {
			returnCode = "NT";
		}
		else if (str.equals("14")) {
			returnCode = "NU";
		}
		
		return returnCode;
	}

}
