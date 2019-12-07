package com.gojek.parkinglot.constants;

public class ParkingLotConstants {
	
	enum ParkingLotCommands{
		CREATE_PARKING_LOT("create_parking_lot", 1),
		PARK("park", 3),
		LEAVE("leave", 1),
		STATUS("status", 0),
		REG_NUM_FOR_CAR_WITH_COLOUR("registration_numbers_for_cars_with_colour", 1),
		SLOT_NUM_FOR_CAR_WITH_COLOUR("slot_numbers_for_cars_with_colour ", 1),
		SLOT_NUM_FOR_REGISTRATION("slot_number_for_registration_number ", 1);
		
		String command;
		int commandLength;
		ParkingLotCommands(String input, int commandLength){
			this.command = input;
			this.commandLength = commandLength;
		}
	}
	
	
}
