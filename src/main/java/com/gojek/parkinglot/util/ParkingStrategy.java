package com.gojek.parkinglot.util;

public interface ParkingStrategy {

	public void add(int slot);
	
	public int getSlot();
	
	public void removeSlot(int slot);
}
