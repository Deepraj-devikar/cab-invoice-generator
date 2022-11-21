package com.cabinvoicegenerator;

import java.text.DecimalFormat;
import java.util.Hashtable;

public class CabInvoiceGenerator {
	private Hashtable<Integer, float[][]> rideRepository;
	private Hashtable<Integer, Integer> rideCountInRepository;
	
	public CabInvoiceGenerator() {
		rideRepository = new Hashtable<Integer, float[][]>();
		rideCountInRepository = new Hashtable<Integer, Integer>();
	}
	
	public class Invoice {
		private int userId;
		private String[][] ridesData;
		private Hashtable<String, Float> totals;
		
		private Invoice(int userId, String[][] ridesData, Hashtable<String, Float> totals) {
			this.userId = userId;
			this.ridesData = ridesData;
			this.totals = totals;
		}

		public int getUserId() {
			return userId;
		}

		public String[][] getRidesData() {
			return ridesData;
		}

		public Hashtable<String, Float> getTotals() {
			return totals;
		}	
	}

	public float calculateFare(float distanceInKiloMeters, float timeInMinutes, float rideType) {
		if(rideType == 0f) {
			float fare = distanceInKiloMeters * 10 + timeInMinutes;
			if(fare > 5f) {
				return fare;
			}
			return 5f;	
		} else {
			float fare = distanceInKiloMeters * 15 + (timeInMinutes * 2);
			if(fare > 20f) {
				return fare;
			}
			return 20f;
		}
	}

	public float multipleRides(float[][] rideData) {
		float fare = 0f;
		for(int rideNumber = 0; rideNumber < rideData.length; rideNumber++) {
			fare += calculateFare(rideData[rideNumber][0], rideData[rideNumber][1], rideData[rideNumber][2]);
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
	
	public void doRide(int userId, float distanceInKiloMeters, float timeInMinutes, float rideType) {
		float[][] rides;
		if(rideRepository.containsKey(userId)) {
			rides = rideRepository.get(userId);
		} else {
			rides = new float[10][2];
			rideRepository.put(userId, rides);
			rideCountInRepository.put(userId, 0);
		}
		float[] ride = {distanceInKiloMeters, timeInMinutes, rideType};
		int ridesCount = rideCountInRepository.get(userId);
		rides[ridesCount++] = ride;
		rideCountInRepository.put(userId, ridesCount);
	}

	public Invoice invoiceService(int userId) {
		float[][] rides = rideRepository.get(userId);
		String[][] ridesData = new String[10][3];
		for(int i = 0; i < rides.length; i++) {
			ridesData[i][0] = String.valueOf(rides[i][0]);
			ridesData[i][1] = String.valueOf(rides[i][1]);
			if(rides[i][2] == 0f) {
				ridesData[i][2] = "Normal";
			} else {
				ridesData[i][2] = "Premium";
			}
		}
		return new Invoice(userId, ridesData, invoice(rides));
	}

}
