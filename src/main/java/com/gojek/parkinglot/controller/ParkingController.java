package com.gojek.parkinglot.controller;

import com.gojek.parkinglot.service.ParkingService;
import com.gojek.parkinglot.service.impl.ParkingServiceImpl;

public class ParkingController {

	private ParkingService parkingSvc = null;
	
	public ParkingController(){
		this.parkingSvc = new ParkingServiceImpl();
	}
	
	public void validate(String input){
		
	}
	
	public void executeCommand(String input){
		
	}
}
