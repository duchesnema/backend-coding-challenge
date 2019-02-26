package com.springboot.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public final class KmpStringMatcherTest {
	
	/* Test suite */
	
	@Test public void testA() {
		String txt = "London"; 
	    String pat = "Londo"; 
	    boolean val = new KMPStringMatching().KMPSearch(pat, txt); 
	    
	    assertEquals(true, val);
	}
	
	


	

}
