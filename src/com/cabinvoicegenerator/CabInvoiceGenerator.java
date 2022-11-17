package com.cabinvoicegenerator;

public class CabInvoiceGenerator {

	public float calculateFare(float distanceInKiloMeters, float timeInMinutes) {
//		distanceInKiloMeters = Math.round(distanceInKiloMeters*100)/100;
//		timeInMinutes = Math.round(timeInMinutes*100)/100;
		float fare = distanceInKiloMeters * 10 + timeInMinutes;
		if(fare > 5f) {
			return fare;
		}
		return 5;
	}

}
