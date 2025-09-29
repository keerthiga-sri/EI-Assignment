package com.smartoffice.commands;

import com.smartoffice.*;

public class CancelBookingCommand implements Command {
    private final int roomId;
    public CancelBookingCommand(int roomId) { this.roomId = roomId; }
    @Override
    public void execute() {
        Room r = Office.getInstance().getRoom(roomId);
        if (r == null) { System.out.println("Invalid room number. Please enter a valid room number."); return; }
        boolean ok = r.cancelBooking();
        if (!ok) System.out.println("Room " + roomId + " is not booked. Cannot cancel booking.");
    }
}
