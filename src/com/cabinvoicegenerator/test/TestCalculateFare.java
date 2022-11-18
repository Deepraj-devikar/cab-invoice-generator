package com.cabinvoicegenerator.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.cabinvoicegenerator.CabInvoiceGenerator;

@RunWith(Parameterized.class)
public class TestCalculateFare {
	private float distanceInKiloMeters, timeInMinutes, expectedFare;
	private CabInvoiceGenerator cabInvoiceGenerator;
	@Before
	public void setUp() throws Exception {
		cabInvoiceGenerator = new CabInvoiceGenerator();
	}
	
	public TestCalculateFare(float distanceInKiloMeters, float timeInMinutes, float expectedFare) {
		this.distanceInKiloMeters = distanceInKiloMeters;
		this.timeInMinutes = timeInMinutes;
		this.expectedFare = expectedFare;
	}
	
	@Parameterized.Parameters
	public static Collection inputs() {
		return Arrays.asList(new Object[][] {
			{15, 54, 204},
			{0.21f, 0.2f, 5},
			{0.5f, 0.2f, 5.2f},
			{0.1f, 3.4f, 5},
			{0.1f, 5, 6f},
			{0, 10, 10},
			{10, 0, 100},
			{1, 1, 11},
		});
	}
	
	@Test
	public void test() {
		Assert.assertEquals(expectedFare, cabInvoiceGenerator.calculateFare(distanceInKiloMeters, timeInMinutes), 0.001);
	}

}
