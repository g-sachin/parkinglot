package com.gojek.parkinglot.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.gojek.parkinglot.service.ParkingService;

public class ParkingControllerTest {

	@Mock
	ParkingService parkingSvc;

	@InjectMocks
	ParkingController controller;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateParkinglot_OK() throws Exception {
		Mockito.doNothing().when(parkingSvc).createParkingLot(6);

		controller.executeCommand("create_parking_lot 6");
		Mockito.verify(parkingSvc).createParkingLot(6);
	}

	@Test
	public void testCreateParkinglot_throwException() throws Exception {
		Mockito.doThrow(Exception.class).when(parkingSvc).createParkingLot(6);

		controller.executeCommand("create_parking_l 6");
	}

	@Test
	public void testParkVehicle_OK() throws Exception {
		Mockito.when(parkingSvc.parkVehicle("HR-26-SD-0007", "White"))
				.thenReturn(1);

		controller.executeCommand("park HR-26-SD-0007 White");
		Mockito.verify(parkingSvc).parkVehicle("HR-26-SD-0007", "White");
	}

	@Test
	public void testLeaveVehicle() throws Exception {
		Mockito.doNothing().when(parkingSvc).leaveSlot(4);
		controller.executeCommand("leave 4");
		
		
		Mockito.verify(parkingSvc).leaveSlot(4);
	}
	
	@Test
	public void testGetStatus() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("1 HR-23-ASWE 5543 Blue");
		Mockito.when(parkingSvc.status()).thenReturn(list);
		controller.executeCommand("status");
		
		Mockito.verify(parkingSvc).status();
	}
	
	@Test
	public void testGetRegWithColours() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("HR-23-ASWQ 2332");
		Mockito.when(parkingSvc.getRegCarWithColours("White")).thenReturn(list);
		controller.executeCommand("registration_numbers_for_cars_with_colour White");
		
		Mockito.verify(parkingSvc).getRegCarWithColours("White");
	}
	
	@Test
	public void testGetSlotwithColor() throws Exception {
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(4);
		Mockito.when(parkingSvc.getSlotsWithColours("White")).thenReturn(list);
		controller.executeCommand("slot_numbers_for_cars_with_colour White");
	
		Mockito.verify(parkingSvc).getSlotsWithColours("White");
	}
	
	@Test
	public void testGetSlowForRegNo() throws Exception {
		Mockito.when(parkingSvc.getSlotsForRegNo("KA-01-HH-3141")).thenReturn(1);
		controller.executeCommand("slot_number_for_registration_number KA-01-HH-3141");
	
		Mockito.verify(parkingSvc).getSlotsForRegNo("KA-01-HH-3141");
	}
}
