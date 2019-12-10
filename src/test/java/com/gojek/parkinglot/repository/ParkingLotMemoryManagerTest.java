package com.gojek.parkinglot.repository;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.repository.impl.ParkingLotMemoryManagerImpl;


public class ParkingLotMemoryManagerTest {
	private ParkingLotMemoryManager manager;

	@Before
	public void setup(){
		manager = ParkingLotMemoryManagerImpl.getInstance();
	}
	
	@Test(expected=Exception.class)
	public void testValidateParkingLot_ParkingNotPresent() throws Exception {
		manager.validateParkingLot();
	}
	
	@Test
	public void testCreateParkingLot() throws Exception {
		manager.createParkingLot(6);
	}
	
	@Test
	public void testParkingLot_OK() throws Exception {
		manager.validateParkingLot();
	}

	@Test
	public void testParkVehicle_leaveVehicle() throws Exception {
		Map map = Mockito.mock(Map.class);
		Mockito.when(map.get("1")).thenReturn(new Vehicle("HR-26-ASWE 3212", "White"));
		boolean status =  manager.leaveVehicle(1);
		Assert.assertEquals(status, true);
	}
}
