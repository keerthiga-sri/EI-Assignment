package com.smartoffice.commands;

import com.smartoffice.Office;
import com.smartoffice.Room;

public class SetRoomCapacityCommand implements Command {
    private final int roomId;
    private final int capacity;
    public SetRoomCapacityCommand(int roomId, int capacity) {
        this.roomId = roomId; this.capacity = capacity;
    }
    @Override
    public void execute() {
        Room r = Office.getInstance().getRoom(roomId);
        if (r == null) { System.out.println("Invalid room number. Please enter a valid room number."); return; }
        r.setCapacity(capacity);
    }
}
