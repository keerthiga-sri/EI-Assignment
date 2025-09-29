package com.smartoffice;

public class LightController implements OccupancyObserver {
    private final Room room;
    private boolean on = false;
    public LightController(Room room) { this.room = room; }
    @Override
    public void update(boolean occupied) {
        if (occupied && !on) {
            on = true;
            System.out.println("[Light] Room " + room + ": Lights turned ON.");
        } else if (!occupied && on) {
            on = false;
            System.out.println("[Light] Room " + room + ": Lights turned OFF.");
        }
    }
}
