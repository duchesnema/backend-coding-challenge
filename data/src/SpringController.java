package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(
		value = "/suggestion", 
		params = { "q", "latitude", "longitude"})
public class SpringController {
	
	@GetMapping("")
	public com.springboot.controller.Cities getCityWithParam(@RequestParam("q") String q, @RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude ) {
		Cities suggestedCitiesList = new Cities(q,latitude,longitude);
		return suggestedCitiesList;
		
	}
}
