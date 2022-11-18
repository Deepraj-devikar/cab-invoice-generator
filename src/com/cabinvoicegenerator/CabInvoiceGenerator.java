package com.cabinvoicegenerator;

import java.text.DecimalFormat;
import java.util.Hashtable;

public class CabInvoiceGenerator {

	public float calculateFare(float distanceInKiloMeters, float timeInMinutes) {
		float fare = distanceInKiloMeters * 10 + timeInMinutes;
		if(fare > 5f) {
			return fare;
		}
		return 5f;
	}

	public float multipleRides(float[][] rideData) {
		float fare = 0f;
		for(int rideNumber = 0; rideNumber < rideData.length; rideNumber++) {
			fare += calculateFare(rideData[rideNumber][0], rideData[rideNumber][1]);
		}
		return fare;
	}

	public Hashtable<String, Float> invoice(float[][] dataToCalculateFare) {
		Hashtable<String, Float> enhancedInvoice = new Hashtable<String, Float>();
		enhancedInvoice.put("Total Number Of Rides", (float) dataToCalculateFare.length);
		enhancedInvoice.put("Total Fare", multipleRides(dataToCalculateFare));
		enhancedInvoice.put(
				"Average Fare Per Ride", 
				Float.parseFloat(
						new DecimalFormat("0.00")
						.format(
								enhancedInvoice.get("Total Fare") / enhancedInvoice.get("Total Number Of Rides"))
						)
				);
		return enhancedInvoice;
	}

}
