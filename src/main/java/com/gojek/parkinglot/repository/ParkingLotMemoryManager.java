package com.gojek.parkinglot.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.gojek.parkinglot.constants.ParkingLotConstants;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.util.NearestEntryStrategy;
import com.gojek.parkinglot.util.ParkingStrategy;

public class ParkingLotMemoryManager {

	private static ParkingLotMemoryManager instance = null;
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	// Map of slot with vehicle details
	private Map<Integer, Vehicle> parkingLotMap = new ConcurrentHashMap<>();
	private AtomicInteger capacity = new AtomicInteger();
	private AtomicInteger availability = new AtomicInteger();
	private ParkingStrategy parkingStrategy = null;

	private ParkingLotMemoryManager() {
		this.parkingStrategy = new NearestEntryStrategy();
	}

	public static ParkingLotMemoryManager getInstance() {
		if (instance == null) {
			synchronized (ParkingLotMemoryManager.class) {
				if (instance == null)
					instance = new ParkingLotMemoryManager();
			}
		}
		return instance;
	}

	public void createParkingLot(int capacity) throws Exception {
		try {
			lock.writeLock();
			for (int i = 0; i < capacity; i++) {
				parkingLotMap.put(i, null);
			}
		} catch (Exception ex) {
			throw new Exception("Not able to create parking lot");
		} finally {
			lock.writeLock().unlock();
		}
	}

	public boolean validateParkingLot() {
		if (parkingLotMap == null || parkingLotMap.isEmpty())
			return false;
		return true;
	}

	public int parkVehicle(Vehicle vehicle) throws Exception {
		int availableSlot;
		try {
			lock.writeLock();
			if (availability.get() == 0) {
				return ParkingLotConstants.NOT_AVAILABLE;
			} else {
				availableSlot = parkingStrategy.getSlot();
				if (parkingLotMap.containsValue(vehicle))
					return ParkingLotConstants.VEHICLE_ALREADY_EXIST;

				parkingLotMap.put(availableSlot, vehicle);
				availability.decrementAndGet();
				parkingStrategy.removeSlot(availableSlot);
			}
		} catch (Exception ex){
			throw new Exception(ex);
		} finally {
			lock.writeLock().unlock();
		}
		
		return availableSlot;
	}

	public boolean leaveVehicle(int slotNumber) throws Exception {
		try {
			lock.writeLock();
			if (parkingLotMap.get(slotNumber) == null)
				return false;
			availability.incrementAndGet();
			parkingStrategy.add(slotNumber);
			parkingLotMap.put(slotNumber, null);
		} catch (Exception ex){
			throw new Exception(ex);
		} finally {
			lock.writeLock().unlock();
		}
		return true;
	}

	public List<String> getStatus() throws Exception {
		List<String> statusList = new ArrayList<>();
		try {
			lock.writeLock();
			for (int i = 1; i <= capacity.get(); i++) {
				Vehicle vehicle = parkingLotMap.get(i);
				if (vehicle != null) {
					statusList.add(i + "\t\t" + vehicle.getRegNo() + "\t\t"
							+ vehicle.getColour());
				}
			}
		} catch (Exception ex){
			throw new Exception(ex);
		} finally {
			lock.writeLock().unlock();
		}
		return statusList;
	}

	public int getAvailableSlotsCount() {
		return availability.get();
	}

	public List<String> getRegNumberForColor(String color) throws Exception {
		List<String> statusList = new ArrayList<>();

		try {
			lock.writeLock();
			for (int i = 1; i <= capacity.get(); i++) {
				Vehicle vehicle = parkingLotMap.get(i);
				if (vehicle != null && color.equalsIgnoreCase(vehicle.getColour())) {
					statusList.add(vehicle.getRegNo());
				}
			}
		} catch (Exception ex){
			throw new Exception(ex);
		} finally {
			lock.writeLock().unlock();
		}
		return statusList;
	}

	public List<Integer> getSlotNumbersFromCarWithColor(String colour) throws Exception {
		List<Integer> slotList = new ArrayList<>();
		try {
			lock.writeLock();
			for (int i = 1; i <= capacity.get(); i++) {
				Vehicle vehicle = parkingLotMap.get(i);
				if (vehicle != null && colour.equalsIgnoreCase(vehicle.getColour())) {
					slotList.add(i);
				}
			}
		} catch (Exception ex){
			throw new Exception(ex);
		} finally {
			lock.writeLock().unlock();
		}
		return slotList;
	}

	public int getSlotNoForRegistrationNo(String registrationNo) throws Exception {
		int result = ParkingLotConstants.NOT_FOUND;
		try {
			lock.writeLock();
			for (int i = 1; i <= capacity.get(); i++) {
				Vehicle vehicle = parkingLotMap.get(i);
				if (vehicle != null
						&& registrationNo.equalsIgnoreCase(vehicle.getRegNo())) {
					result = i;
				}
			}
		} catch (Exception ex){
			throw new Exception(ex);
		} finally {
			lock.writeLock().unlock();
		}
		
		return result;
	}

}
