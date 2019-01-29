package com.mad.covo_challenge;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {
	
	public static double distanceCity(double y1, double x1, double y2, double x2) {
	
		double a = Math.pow(y2-y1, 2) + Math.pow(x2-x1, 2);
		return Math.sqrt(a);
	}
	
	//Helper from stack overflow https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
