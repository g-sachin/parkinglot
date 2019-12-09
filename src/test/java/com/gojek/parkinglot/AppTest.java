package com.gojek.parkinglot;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.service.ParkingService;
import com.gojek.parkinglot.service.impl.ParkingServiceImpl;


/**
 * Unit test for simple App.
 */
public class AppTest
{
	private final ByteArrayOutputStream	outContent	= new ByteArrayOutputStream();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@After
	public void cleanUp()
	{
		System.setOut(null);
	}
	
	@Test
	public void createParkingLot() throws Exception
	{
		ParkingService instance = new ParkingServiceImpl();
		instance.createParkingLot(65);
		assertTrue("createdparkinglotwith65slots".equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
	}
	
	@Test
	public void alreadyExistParkingLot() throws Exception
	{
		ParkingService instance = new ParkingServiceImpl();
		instance.createParkingLot(65);
		assertTrue("createdparkinglotwith65slots".equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
	}
	
	@Test
	public void testParkingCapacity() throws Exception
	{
		ParkingService instance = new ParkingServiceImpl();
		assertEquals("Sorry,CarParkingDoesnotExist", outContent.toString().trim().replace(" ", ""));
		instance.createParkingLot(11);
		instance.parkVehicle("KA-01-HH-1234", "White");
		instance.parkVehicle("KA-01-HH-9999", "White");
		instance.parkVehicle("KA-01-BB-0001", "Black");
	}
}
