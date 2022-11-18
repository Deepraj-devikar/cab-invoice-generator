package com.cabinvoicegenerator;

public class CabInvoiceGenerator {

	public float calculateFare(float distanceInKiloMeters, float timeInMinutes) {
		float fare = distanceInKiloMeters * 10 + timeInMinutes;
		if(fare > 5f) {
			return fare;
		}
		return 5;
	}

	public float multipleRides(float[][] rideData) {
		float fare = 0f;
		for(int rideNumber = 0; rideNumber < rideData.length; rideNumber++) {
			fare += calculateFare(rideData[rideNumber][0], rideData[rideNumber][1]);
		}
		return fare;
	}

}
