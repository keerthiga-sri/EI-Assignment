package com.smartoffice.commands;

import com.smartoffice.*;
import java.time.*;

public class BookRoomCommand implements Command {
    private final int roomId;
    private final LocalTime start;
    private final int duration;
    public BookRoomCommand(int roomId, LocalTime start, int duration) {
        this.roomId = roomId; this.start = start; this.duration = duration;
    }
    @Override
    public void execute() {
        Room r = Office.getInstance().getRoom(roomId);
        if (r == null) { System.out.println("Invalid room number. Please enter a valid room number."); return; }
        Booking b = new Booking(start, duration);
        boolean ok = r.book(b);
        if (!ok) System.out.println("Room " + roomId + " is already booked during this time. Cannot book.");
    }
}
