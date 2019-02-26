package com.springboot.controller;
import java.util.ArrayList;

public interface Search {
	
	public ArrayList<City> search(ArrayList<City> cities, String name);
}