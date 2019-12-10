package com.gojek.parkinglot.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gojek.parkinglot.constants.ParkingLotConstants;
import com.gojek.parkinglot.service.ParkingService;
import com.gojek.parkinglot.service.impl.ParkingServiceImpl;

public class ParkingController extends GenericController {

	private ParkingService parkingSvc = null;
	private Logger logger = Logger.getLogger(ParkingController.class.getName());

	public ParkingController() {
		this.parkingSvc = new ParkingServiceImpl();
	}

	public void executeCommand(String input) {
		String command[] = input.trim().split(" ");
		String operation = command[0];
		try {
			switch (operation) {
			case ParkingLotConstants.CREATE_PARKING_LOT:
				int capacity = Integer.parseInt(command[1].trim());
				parkingSvc.createParkingLot(capacity);
				logger.info("Created a parking lot with " + capacity+" slots");
				break;
			case ParkingLotConstants.PARK:
				String vehiceRegNo = command[1];
				String colour = command[2];
				int allotedSlot = parkingSvc.parkVehicle(vehiceRegNo, colour);
				if(allotedSlot == ParkingLotConstants.NOT_AVAILABLE)
					logger.info("Sorry, parking lot is full");
				else 
					logger.info("Allocated slot number: "+allotedSlot);
				break;
			case ParkingLotConstants.LEAVE:
				int slot = Integer.parseInt(command[1].trim());
				parkingSvc.leaveSlot(slot);
				logger.info("Slot number "+slot+" is freee");
				break;
			case ParkingLotConstants.STATUS:
				List<String> status = parkingSvc.status();
				logger.info(status.toString());
				break;
			case ParkingLotConstants.REG_NUM_FOR_CAR_WITH_COLOUR:
				parkingSvc.getRegCarWithColours(command[1].trim());
				break;
			case ParkingLotConstants.SLOT_NUM_FOR_CAR_WITH_COLOUR:
				parkingSvc.getSlotsWithColours(command[1].trim());
				break;
			case ParkingLotConstants.SLOT_NUM_FOR_REGISTRATION:
				parkingSvc.getSlotsForRegNo(command[1].trim());
				break;
			default:
				throw new Exception("Invalid operation: " + operation);
			}
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Exception during : " + operation, ex);
		}

	}
}
