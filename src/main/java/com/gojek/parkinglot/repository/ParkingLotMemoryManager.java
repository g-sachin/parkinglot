package com.gojek.parkinglot.repository;

import java.util.List;

import com.gojek.parkinglot.model.Vehicle;

public interface ParkingLotMemoryManager {
	
	public boolean validateParkingLot() throws Exception;
	
	public void createParkingLot(int capacity) throws Exception;
	
	public int parkVehicle(Vehicle vehicle) throws Exception;
	
	public boolean leaveVehicle(int slot) throws Exception;
	
	public List<String> getStatus() throws Exception;
	
	public List<String> getRegNumberForColor(String colour) throws Exception;
	
	public List<Integer> getSlotNumbersFromCarWithColor(String colour) throws Exception;
	
	public int getSlotNoForRegistrationNo(String registrationNo) throws Exception;
	
	public int getAvailableSlotsCount() throws Exception;
}
