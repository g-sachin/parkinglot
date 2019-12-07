package com.gojek.parkinglot.util;

import java.util.TreeSet;

public class NearestEntryStrategy implements ParkingStrategy {

	private TreeSet<Integer> availableSlots;

	public NearestEntryStrategy() {
		availableSlots = new TreeSet<Integer>();
	}

	@Override
	public void add(int slot) {
		availableSlots.add(slot);
	}

	@Override
	public int getSlot() {
		return availableSlots.first();
	}

	@Override
	public void removeSlot(int slot) {
		availableSlots.remove(slot);
	}
}
