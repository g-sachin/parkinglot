package com.gojek.parkinglot.service;

import java.util.List;
import com.gojek.parkinglot.model.Vehicle;

public interface ParkingService {

	public void createParkingLot(int capacity);
	
	public void leaveSlot(int slot);
	
	public void status();
	
	public List<Vehicle> getRegCarWithColours(String colour);
	
	public List<Integer> getSlotsWithColours(String regNo);
	
	public List<Integer> getSlotsForRegNo(String regNo);
}
