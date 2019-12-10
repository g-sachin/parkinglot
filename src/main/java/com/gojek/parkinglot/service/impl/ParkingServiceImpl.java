package com.gojek.parkinglot.service.impl;

import java.util.List;
import java.util.logging.Logger;

import com.gojek.parkinglot.constants.ParkingLotConstants;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.repository.ParkingLotMemoryManager;
import com.gojek.parkinglot.repository.impl.ParkingLotMemoryManagerImpl;
import com.gojek.parkinglot.service.ParkingService;

public class ParkingServiceImpl implements ParkingService {

	private ParkingLotMemoryManager memoryManager = null;
	private Logger logger = Logger.getLogger(ParkingServiceImpl.class.getName());
	
	public ParkingServiceImpl(){
		this.memoryManager = ParkingLotMemoryManagerImpl.getInstance();
	}
	
	@Override
	public void createParkingLot(int capacity) throws Exception {
		memoryManager.createParkingLot(capacity);
	}

	@Override
	public void leaveSlot(int slot) throws Exception {
		memoryManager.validateParkingLot();
		memoryManager.leaveVehicle(slot);
	}

	@Override
	public List<String> status() throws Exception {
		memoryManager.validateParkingLot();
		return memoryManager.getStatus();
	}

	@Override
	public List<String> getRegCarWithColours(String colour) throws Exception {
		memoryManager.validateParkingLot();
		List<String> vehicleList = memoryManager.getRegNumberForColor(colour);
		logger.info(vehicleList.toString());
		return vehicleList;
	}

	@Override
	public List<Integer> getSlotsWithColours(String colour) throws Exception {
		memoryManager.validateParkingLot();
		List<Integer> slotList = memoryManager.getSlotNumbersFromCarWithColor(colour);
		logger.info(slotList.toString());
		return slotList;
	}

	@Override
	public Integer getSlotsForRegNo(String regNo) throws Exception {
		memoryManager.validateParkingLot();
		int slot = memoryManager.getSlotNoForRegistrationNo(regNo);
		String result = slot != ParkingLotConstants.NOT_FOUND ? Integer.toString(slot) : "Not Found";
		logger.info(result);
		return slot;
	}

	@Override
	public int parkVehicle(String vehiceRegNo, String colour) throws Exception {
		int status = memoryManager.parkVehicle(new Vehicle(vehiceRegNo, colour));
		if(status != ParkingLotConstants.NOT_AVAILABLE)
			logger.info("Parked Vehicle RegNo: "+vehiceRegNo+" , colour: "+colour);
		return status;
	}

}
