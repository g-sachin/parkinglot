package com.gojek.parkinglot.service.impl;

import java.util.List;
import java.util.logging.Logger;

import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.repository.ParkingLotMemoryManagerTest;
import com.gojek.parkinglot.service.ParkingService;

public class ParkingServiceImpl implements ParkingService {

	private ParkingLotMemoryManagerTest memoryManager = null;
	private Logger logger = Logger.getLogger(ParkingServiceImpl.class.getName());
	
	public ParkingServiceImpl(){
		this.memoryManager = ParkingLotMemoryManagerTest.getInstance();
	}
	
	@Override
	public void createParkingLot(int capacity) throws Exception {
		memoryManager.createParkingLot(capacity);
	}

	@Override
	public void leaveSlot(int slot) throws Exception {
		memoryManager.leaveVehicle(slot);
	}

	@Override
	public void status() throws Exception {
		memoryManager.getStatus();
	}

	@Override
	public List<String> getRegCarWithColours(String colour) throws Exception {
		List<String> vehicleList = memoryManager.getRegNumberForColor(colour);
		logger.info(vehicleList.toString());
		return vehicleList;
	}

	@Override
	public List<Integer> getSlotsWithColours(String colour) throws Exception {
		List<Integer> slotList = memoryManager.getSlotNumbersFromCarWithColor(colour);
		logger.info(slotList.toString());
		return slotList;
	}

	@Override
	public Integer getSlotsForRegNo(String regNo) throws Exception {
		int slot = memoryManager.getSlotNoForRegistrationNo(regNo);
		String result = (String) (slot != -1 ? slot : "Not Found");
		logger.info(result);
		return slot;
	}

	@Override
	public void parkVehicle(String vehiceRegNo, String colour) throws Exception {
		memoryManager.parkVehicle(new Vehicle(vehiceRegNo, colour));
	}

}
