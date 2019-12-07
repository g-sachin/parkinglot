package com.gojek.parkinglot.service;

import java.util.List;

public interface ParkingService {

	public void createParkingLot(int capacity) throws Exception;
	
	public void parkVehicle(String vehiceRegNo, String colour) throws Exception;
	
	public void leaveSlot(int slot) throws Exception;
	
	public void status() throws Exception;
	
	public List<String> getRegCarWithColours(String colour) throws Exception;
	
	public List<Integer> getSlotsWithColours(String regNo) throws Exception;
	
	public Integer getSlotsForRegNo(String regNo) throws Exception;
}
