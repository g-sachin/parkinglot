package com.gojek.parkinglot.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.gojek.parkinglot.constants.ParkingLotConstants;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.repository.ParkingLotMemoryManager;
import com.gojek.parkinglot.util.NearestEntryStrategy;
import com.gojek.parkinglot.util.ParkingStrategy;

public class ParkingLotMemoryManagerImpl implements ParkingLotMemoryManager{

	private static ParkingLotMemoryManagerImpl instance = null;
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	// Map of slot with vehicle details
	private Map<Integer, Vehicle> parkingLotMap;
	private AtomicInteger capacity = new AtomicInteger();
	private AtomicInteger availability = new AtomicInteger();
	private ParkingStrategy parkingStrategy = null;

	private ParkingLotMemoryManagerImpl() {
		parkingLotMap = new ConcurrentHashMap<>();
		this.parkingStrategy = new NearestEntryStrategy();
	}

	public static ParkingLotMemoryManagerImpl getInstance() {
		if (instance == null) {
			synchronized (ParkingLotMemoryManagerImpl.class) {
				if (instance == null)
					instance = new ParkingLotMemoryManagerImpl();
			}
		}
		return instance;
	}
	
	@Override
	public void createParkingLot(int capacity) throws Exception {
		try {
			lock.writeLock().lock();
			this.capacity.set(capacity);
			this.availability.set(capacity);
			for (int i = 1; i <= capacity; i++) {
				parkingLotMap.put(i, new Vehicle(null, null));
				parkingStrategy.add(i);
			}
		} catch (Exception ex) {
			throw new Exception("Not able to create parking lot");
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public boolean validateParkingLot() throws Exception {
		if (parkingLotMap == null || parkingLotMap.isEmpty())
			throw new Exception("Parking lot not present");
		return true;
	}

	@Override
	public int parkVehicle(Vehicle vehicle) throws Exception {
		int availableSlot;
		try {
			lock.writeLock().lock();
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

	@Override
	public boolean leaveVehicle(int slotNumber) throws Exception {
		try {
			lock.writeLock().lock();
			if (parkingLotMap.get(slotNumber) == null)
				return false;
			availability.incrementAndGet();
			parkingStrategy.add(slotNumber);
			parkingLotMap.put(slotNumber, new Vehicle(null, null));
		} catch (Exception ex){
			throw new Exception(ex);
		} finally {
			lock.writeLock().unlock();
		}
		return true;
	}

	@Override
	public List<String> getStatus() throws Exception {
		List<String> statusList = new ArrayList<>();
		statusList.add("Slot No. \t\t Registration No \t\t Colour \n");
		try {
			lock.readLock().lock();;
			for (int i = 1; i <= capacity.get(); i++) {
				Vehicle vehicle = parkingLotMap.get(i);
				if (vehicle != null) {
					statusList.add(i + "\t\t" + vehicle.getRegNo() + "\t\t"
							+ vehicle.getColour());
					statusList.add("\n");
				}
			}
		} catch (Exception ex){
			throw new Exception(ex);
		} finally {
			lock.readLock().unlock();
		}
		return statusList;
	}

	@Override
	public int getAvailableSlotsCount() {
		return availability.get();
	}

	@Override
	public List<String> getRegNumberForColor(String color) throws Exception {
		List<String> statusList = new ArrayList<>();

		try {
			lock.readLock().lock();
			for (int i = 1; i <= capacity.get(); i++) {
				Vehicle vehicle = parkingLotMap.get(i);
				if (vehicle != null && color.equalsIgnoreCase(vehicle.getColour())) {
					statusList.add(vehicle.getRegNo());
				}
			}
		} catch (Exception ex){
			throw new Exception(ex);
		} finally {
			lock.readLock().unlock();
		}
		return statusList;
	}

	@Override
	public List<Integer> getSlotNumbersFromCarWithColor(String colour) throws Exception {
		List<Integer> slotList = new ArrayList<>();
		try {
			lock.readLock().lock();
			for (int i = 1; i <= capacity.get(); i++) {
				Vehicle vehicle = parkingLotMap.get(i);
				if (vehicle != null && colour.equalsIgnoreCase(vehicle.getColour())) {
					slotList.add(i);
				}
			}
		} catch (Exception ex){
			throw new Exception(ex);
		} finally {
			lock.readLock().unlock();
		}
		return slotList;
	}

	@Override
	public int getSlotNoForRegistrationNo(String registrationNo) throws Exception {
		int result = ParkingLotConstants.NOT_FOUND;
		try {
			lock.readLock().lock();
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
			lock.readLock().unlock();
		}
		
		return result;
	}

}
