package com.smartoffice.commands;

import com.smartoffice.*;

public class UpdateOccupancyCommand implements Command {
    private final int roomId;
    private final int count;
    public UpdateOccupancyCommand(int roomId, int count) { this.roomId = roomId; this.count = count; }
    @Override
    public void execute() {
        Room r = Office.getInstance().getRoom(roomId);
        if (r == null) { System.out.println("Room " + roomId + " does not exist."); return; }
        r.updateOccupants(count);
    }
}
