package com.cabinvoicegenerator.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.cabinvoicegenerator.CabInvoiceGenerator;
import com.cabinvoicegenerator.CabInvoiceGenerator.Invoice;;

@RunWith(Parameterized.class)
public class TestInvoiceService {
	private int userId;
	private float[] distancesInKiloMeters;
	private float[] timesInMinutes;
	private CabInvoiceGenerator cabInvoiceGenerator;
	
	@Before
	public void setUp() throws Exception {
		cabInvoiceGenerator = new CabInvoiceGenerator();
	}
	
	public TestInvoiceService(int userId, float[] distancesInKiloMeters, float[] timesInMinutes) {
		this.userId = userId;
		this.distancesInKiloMeters = distancesInKiloMeters;
		this.timesInMinutes = timesInMinutes;
	}

	@Parameterized.Parameters
	public static Collection testInputs() {
		
		Object[][] inputs = new Object[2][3];
		
		float[] distancesInKiloMeters;
		float[] timesInMinutes;
		Hashtable<String, Float> expectedInvoice;
		
		distancesInKiloMeters = new float[8];
		timesInMinutes = new float[8];
		distancesInKiloMeters[0] = 15f;
		distancesInKiloMeters[1] = 0.21f;
		distancesInKiloMeters[2] = 0.5f;
		distancesInKiloMeters[3] = 0.1f;
		distancesInKiloMeters[4] = 0.1f;
		distancesInKiloMeters[5] = 0f;
		distancesInKiloMeters[6] = 10f;
		distancesInKiloMeters[7] = 1f;
		timesInMinutes[0] = 5f;
		timesInMinutes[1] = 0.2f;
		timesInMinutes[2] = 0.2f;
		timesInMinutes[3] = 3.4f;
		timesInMinutes[4] = 5f;
		timesInMinutes[5] = 10f;
		timesInMinutes[6] = 0f;
		timesInMinutes[7] = 1f;
		inputs[0][0] = 1000;
		inputs[0][1] = distancesInKiloMeters;
		inputs[0][2] = timesInMinutes;
		
		distancesInKiloMeters = new float[8];
		timesInMinutes = new float[8];
		distancesInKiloMeters[0] = 1f;
		distancesInKiloMeters[1] = 0.21f;
		distancesInKiloMeters[2] = 0.5f;
		distancesInKiloMeters[3] = 0.1f;
		distancesInKiloMeters[4] = 0.1f;
		distancesInKiloMeters[5] = 0f;
		distancesInKiloMeters[6] = 10f;
		distancesInKiloMeters[7] = 1f;
		timesInMinutes[0] = 54f;
		timesInMinutes[1] = 0.2f;
		timesInMinutes[2] = 0.2f;
		timesInMinutes[3] = 3.4f;
		timesInMinutes[4] = 5f;
		timesInMinutes[5] = 10f;
		timesInMinutes[6] = 20f;
		timesInMinutes[7] = 1f;
		inputs[1][0] = 1001;
		inputs[1][1] = distancesInKiloMeters;
		inputs[1][2] = timesInMinutes;
		
		return Arrays.asList(inputs);
	}
	@Test
	public void test() {
		float[][] dataToCalculateFare = new float[distancesInKiloMeters.length][2];
		for(int i = 0; i < distancesInKiloMeters.length; i++) {
			dataToCalculateFare[i][0] = distancesInKiloMeters[i];
			dataToCalculateFare[i][1] = timesInMinutes[i];
		}
		Invoice invoice = cabInvoiceGenerator.invoiceService(userId, dataToCalculateFare);
		Assert.assertEquals(userId, invoice.getUserId());
		float[][] ridesData = invoice.getRidesData();
		for(int i = 0; i < distancesInKiloMeters.length; i++) {
			Assert.assertArrayEquals(dataToCalculateFare[i], ridesData[i], 0.001f);
		}
		Assert.assertEquals(cabInvoiceGenerator.invoice(dataToCalculateFare), invoice.getTotals());
	}

}
