package com.cabinvoicegenerator.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.cabinvoicegenerator.CabInvoiceGenerator;
import com.cabinvoicegenerator.CabInvoiceGenerator.Invoice;

@RunWith(Parameterized.class)
public class TestPreminumRides {
	private int userId;
	private float[] distancesInKiloMeters;
	private float[] timesInMinutes;
	private float[] rideTypes;
	private CabInvoiceGenerator cabInvoiceGenerator;
	
	@Before
	public void setUp() throws Exception {
		cabInvoiceGenerator = new CabInvoiceGenerator();
	}
	
	public TestPreminumRides(int userId, float[] distancesInKiloMeters, float[] timesInMinutes, float[] rideTypes) {
		this.userId = userId;
		this.distancesInKiloMeters = distancesInKiloMeters;
		this.timesInMinutes = timesInMinutes;
		this.rideTypes = rideTypes;
	}
	
	@Parameterized.Parameters
	public static Collection testInputs() {
		
		Object[][] inputs = new Object[2][4];
		
		float[] distancesInKiloMeters;
		float[] timesInMinutes;
		float[] rideTypes;
		Hashtable<String, Float> expectedInvoice;
		
		distancesInKiloMeters = new float[10];
		timesInMinutes = new float[10];
		rideTypes = new float[10];
		distancesInKiloMeters[0] = 15f;
		distancesInKiloMeters[1] = 0.21f;
		distancesInKiloMeters[2] = 0.5f;
		distancesInKiloMeters[3] = 0.1f;
		distancesInKiloMeters[4] = 0.1f;
		distancesInKiloMeters[5] = 0f;
		distancesInKiloMeters[6] = 10f;
		distancesInKiloMeters[7] = 1f;
		distancesInKiloMeters[8] = 0f;
		distancesInKiloMeters[9] = 10f;
		timesInMinutes[0] = 5f;
		timesInMinutes[1] = 0.2f;
		timesInMinutes[2] = 0.2f;
		timesInMinutes[3] = 3.4f;
		timesInMinutes[4] = 5f;
		timesInMinutes[5] = 10f;
		timesInMinutes[6] = 0f;
		timesInMinutes[7] = 1f;
		timesInMinutes[8] = 10f;
		timesInMinutes[9] = 0f;
		rideTypes[0] = 0f;
		rideTypes[1] = 0f;
		rideTypes[2] = 0f;
		rideTypes[3] = 1f;
		rideTypes[4] = 1f;
		rideTypes[5] = 1f;
		rideTypes[6] = 0f;
		rideTypes[7] = 1f;
		rideTypes[8] = 1f;
		rideTypes[9] = 0f;
		inputs[0][0] = 1;
		inputs[0][1] = distancesInKiloMeters;
		inputs[0][2] = timesInMinutes;
		inputs[0][3] = rideTypes;
		
		distancesInKiloMeters = new float[10];
		timesInMinutes = new float[10];
		rideTypes = new float[10];
		distancesInKiloMeters[0] = 1f;
		distancesInKiloMeters[1] = 0.21f;
		distancesInKiloMeters[2] = 0.5f;
		distancesInKiloMeters[3] = 0.1f;
		distancesInKiloMeters[4] = 0.1f;
		distancesInKiloMeters[5] = 0f;
		distancesInKiloMeters[6] = 10f;
		distancesInKiloMeters[7] = 1f;
		distancesInKiloMeters[8] = 0f;
		distancesInKiloMeters[9] = 10f;
		timesInMinutes[0] = 54f;
		timesInMinutes[1] = 0.2f;
		timesInMinutes[2] = 0.2f;
		timesInMinutes[3] = 3.4f;
		timesInMinutes[4] = 5f;
		timesInMinutes[5] = 10f;
		timesInMinutes[6] = 20f;
		timesInMinutes[7] = 1f;
		timesInMinutes[8] = 10f;
		timesInMinutes[9] = 20f;
		rideTypes[0] = 0f;
		rideTypes[1] = 0f;
		rideTypes[2] = 0f;
		rideTypes[3] = 1f;
		rideTypes[4] = 1f;
		rideTypes[5] = 1f;
		rideTypes[6] = 0f;
		rideTypes[7] = 1f;
		rideTypes[8] = 1f;
		rideTypes[9] = 0f;
		inputs[1][0] = 11;
		inputs[1][1] = distancesInKiloMeters;
		inputs[1][2] = timesInMinutes;
		inputs[1][3] = rideTypes;
		
		return Arrays.asList(inputs);
	}

	@Test
	public void test() {
		float[][] dataToCalculateFare = new float[distancesInKiloMeters.length][3];
		for(int i = 0; i < distancesInKiloMeters.length; i++) {
			dataToCalculateFare[i][0] = distancesInKiloMeters[i];
			dataToCalculateFare[i][1] = timesInMinutes[i];
			dataToCalculateFare[i][2] = rideTypes[i];
			cabInvoiceGenerator.doRide(userId, distancesInKiloMeters[i], timesInMinutes[i], rideTypes[i]);
		}
		Invoice invoice = cabInvoiceGenerator.invoiceService(userId);
		Assert.assertEquals(cabInvoiceGenerator.invoice(dataToCalculateFare), invoice.getTotals());
	}

}
