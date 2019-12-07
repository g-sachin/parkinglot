package com.gojek.parkinglot.controller;

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
				logger.info("Creating parking lot with capacity: " + capacity);
				parkingSvc.createParkingLot(capacity);
				break;
			case ParkingLotConstants.PARK:
				String vehiceRegNo = command[1];
				String colour = command[2];
				parkingSvc.parkVehicle(vehiceRegNo, colour);
				break;
			case ParkingLotConstants.LEAVE:
				parkingSvc.leaveSlot(Integer.parseInt(command[1].trim()));
				break;
			case ParkingLotConstants.STATUS:
				parkingSvc.status();
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
