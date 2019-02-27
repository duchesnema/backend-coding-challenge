package com.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class SpringController {
	
	@RequestMapping(value = "/suggestion", method = RequestMethod.GET)
	public Cities getCityWithParam(	@RequestParam("q") String q, 
									@RequestParam(value = "latitude", required=false, defaultValue = "0") double latitude, 
									@RequestParam(value = "longitude", required=false, defaultValue = "0") double longitude ) {
		Cities suggestedCitiesList = new Cities(q,latitude,longitude);
		return suggestedCitiesList;
		
	}
}
