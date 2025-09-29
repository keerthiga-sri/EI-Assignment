package com.smartoffice;

import java.time.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Room {
    private final int id;
    private int capacity = 10;
    private AtomicInteger occupants = new AtomicInteger(0);
    private Booking booking = null;
    private boolean occupied = false;
    private final List<OccupancyObserver> observers = new ArrayList<>();
    private Instant lastUnoccupiedTime = null;

    public Room(int id) {
        this.id = id;
        // add default observers: AC and Light
        observers.add(new ACController(this));
        observers.add(new LightController(this));
    }

    public synchronized void setCapacity(int c) {
        if (c <= 0) throw new IllegalArgumentException("Invalid capacity. Please enter a valid positive number.");
        this.capacity = c;
        System.out.println("Room " + id + " maximum capacity set to " + c + ".");
    }

    public synchronized boolean book(Booking b) {
        if (booking != null && booking.overlaps(b)) return false;
        this.booking = b;
        System.out.println("Room " + id + " booked from " + b.start + " for " + b.durationMinutes + " minutes.");
        return true;
    }

    public synchronized boolean cancelBooking() {
        if (booking == null) return false;
        booking = null;
        System.out.println("Booking for Room " + id + " cancelled successfully.");
        return true;
    }

    public synchronized void updateOccupants(int count) {
        if (count < 0) throw new IllegalArgumentException("Invalid occupant count.");
        occupants.set(count);
        if (count >= 2) {
            if (!occupied) {
                occupied = true;
                notifyObservers(true);
                System.out.println("Room " + id + " is now occupied by " + count + " persons. AC and lights turned on.");
            } else {
                System.out.println("Room " + id + " occupancy updated to " + count + ".");
            }
        } else {
            if (occupied) {
                occupied = false;
                lastUnoccupiedTime = Instant.now();
                notifyObservers(false);
                System.out.println("Room " + id + " is now unoccupied. AC and lights turned off.");
                // auto-release booking if unoccupied beyond 5 minutes
                Office.getInstance().schedule(() -> {
                    synchronized (Room.this) {
                        if (!occupied && booking != null) {
                            // check elapsed
                            if (lastUnoccupiedTime != null && Duration.between(lastUnoccupiedTime, Instant.now()).toMinutes() >= 5) {
                                booking = null;
                                System.out.println("Room " + id + " was unoccupied for >5 mins. Booking released. AC and lights off.");
                            }
                        }
                    }
                }, 5 * 60 * 1000);
            } else {
                System.out.println("Room " + id + " occupancy insufficient to mark as occupied.");
            }
        }
    }

    public synchronized String statusReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Room ").append(id).append(": ");
        sb.append(occupied ? "Occupied" : "Unoccupied");
        sb.append(", Occupants=").append(occupants.get());
        sb.append(", Capacity=").append(capacity);
        sb.append(", Booking=").append(booking == null ? "None" : booking.toString());
        return sb.toString();
    }

    public String summaryLine() {
        return "Room " + id + " - " + (occupied ? "Occupied" : "Unoccupied") + " - Occupants: " + occupants.get() + " - Booking: " + (booking==null?"None":booking.toString());
    }

    private void notifyObservers(boolean occupied) {
        for (OccupancyObserver o : observers) o.update(occupied);
    }
}
