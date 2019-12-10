package com.gojek.parkinglot.service.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.repository.ParkingLotMemoryManager;
import com.gojek.parkinglot.service.ParkingService;

public class ParkingServiceImplTest {

	@Mock
	private ParkingLotMemoryManager manager;
	
	@InjectMocks
	private ParkingService parkingSvc = new ParkingServiceImpl();
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateParkingLot() throws Exception{
		Mockito.doNothing().when(manager).createParkingLot(6);
		parkingSvc.createParkingLot(6);
		Mockito.verify(manager).createParkingLot(6);
	}
	
	@Test
	public void testParkVehicle() throws Exception{
		Mockito.when(manager.parkVehicle(new Vehicle("HR-26-SD-0007", "White"))).thenReturn(0);
		int status = parkingSvc.parkVehicle("HR-24-AASS 4221", "Blue");
		System.out.println("staus: "+status);
		Assert.assertEquals(status, 0);
		
	}
	
	@Test
	public void testLeaveVehicle() throws Exception {
		Mockito.when(manager.validateParkingLot()).thenReturn(true);
		Mockito.when(manager.leaveVehicle(1)).thenReturn(true);
		parkingSvc.leaveSlot(1);
		
		Mockito.verify(manager).leaveVehicle(1);
	}
	
	@Test
	public void testGetStatus() throws Exception {
		List<String> ls = new ArrayList<>();
		ls.add("1 HR-25-AAWQ 4321 Blue");
		Mockito.when(manager.getStatus()).thenReturn(ls);
		List<String> result = parkingSvc.status();
		Assert.assertEquals(result, ls);
	}

	@Test
	public void testGetRegNoWithColor() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("HR-23-AQWS Blue");
		Mockito.when(manager.getRegNumberForColor("Blue")).thenReturn(list);
		List<String> result = parkingSvc.getRegCarWithColours("Blue");
		Assert.assertEquals(result, list);
	}
	
	@Test
	public void testGetSlotWithRegNo() throws Exception {
		Mockito.when(manager.getSlotNoForRegistrationNo("HR-23-AWSQ Blue")).thenReturn(0);
		int slot = parkingSvc.getSlotsForRegNo("HR-23-ASW Blue");
		Assert.assertEquals(slot, 0);
	}
	
	@Test
	public void testGetSlotWithColor() throws Exception {
		List<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(4);
		Mockito.when(manager.getSlotNumbersFromCarWithColor("Blue")).thenReturn(list);
		List<Integer> result = parkingSvc.getSlotsWithColours("Blue");
		Assert.assertEquals(result, list);
	}
}
