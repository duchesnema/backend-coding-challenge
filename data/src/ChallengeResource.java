package com.mad.covo_challenge;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("suggestion")
public class ChallengeResource {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cities getCityWithParam(@QueryParam("q") String city, @QueryParam("latitude") double lat, @QueryParam("longitude") double longitude) {

		Cities suggestedCitiesList = new Cities(city,lat,longitude);
		return suggestedCitiesList;
    }
}


