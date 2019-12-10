package com.gojek.parkinglot.constants;


public interface ParkingLotConstants {
	
	public static final String CREATE_PARKING_LOT = "create_parking_lot";
	public static final String PARK = "park";
	public static final String LEAVE = "leave";
	public static final String STATUS = "status";
	public static final String REG_NUM_FOR_CAR_WITH_COLOUR = "registration_numbers_for_cars_with_colour";
	public static final String SLOT_NUM_FOR_CAR_WITH_COLOUR = "slot_numbers_for_cars_with_colour";
	public static final String SLOT_NUM_FOR_REGISTRATION = "slot_number_for_registration_number";
	
	public static final int NOT_AVAILABLE = -1;
	public static final int	VEHICLE_ALREADY_EXIST = 409;
	public static final int NOT_FOUND = 404;
}
