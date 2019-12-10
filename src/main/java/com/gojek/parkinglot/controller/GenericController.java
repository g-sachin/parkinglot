package com.gojek.parkinglot.controller;

import java.util.logging.Logger;

public class GenericController {
	private Logger logger = Logger.getGlobal();
	public boolean validate(String input) throws Exception{
		if (input == null || input.isEmpty())
			throw new Exception("Invalid input");
		
		String params[] = input.trim().split(" ");
		String command = params[0];
		
		logger.info("Executing command **********<"+input+">**********");
		return ParkingLotCommands.validate(command, params.length);
	}
	

	enum ParkingLotCommands{
		CREATE_PARKING_LOT("create_parking_lot", 2),
		PARK("park", 3),
		LEAVE("leave", 2),
		STATUS("status", 1),
		REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR("registration_numbers_for_cars_with_colour", 2),
		SLOT_NUMBERS_FOR_CARS_WITH_COLOUR("slot_numbers_for_cars_with_colour", 2),
		SLOT_NUMBER_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number", 2);
		
		String command;
		int commandLength;
		ParkingLotCommands(String input, int commandLength){
			this.command = input;
			this.commandLength = commandLength;
		}
		
		public int getCommandLenght(){
			return commandLength;
		}
		
		public static boolean validate(String input, int argLength){
			for(ParkingLotCommands command : ParkingLotCommands.values()){
				//check the command name and no of arguments
				if(command.toString().equalsIgnoreCase(input) && argLength == command.getCommandLenght())
					return true;
			}
			return false;
		}
		
	}
}
