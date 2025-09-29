package com.smartoffice;

public class ACController implements OccupancyObserver {
    private final Room room;
    private boolean on = false;
    public ACController(Room room) { this.room = room; }
    @Override
    public void update(boolean occupied) {
        if (occupied && !on) {
            on = true;
            // simulate action
            System.out.println("[AC] Room " + room + ": AC turned ON.");
        } else if (!occupied && on) {
            on = false;
            System.out.println("[AC] Room " + room + ": AC turned OFF.");
        }
    }
}
