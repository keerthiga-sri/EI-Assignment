package com.smartoffice;

import java.time.*;

public class Booking {
    public final LocalTime start;
    public final int durationMinutes;

    public Booking(LocalTime start, int durationMinutes) {
        this.start = start;
        this.durationMinutes = durationMinutes;
    }

    public boolean overlaps(Booking other) {
        LocalTime a1 = start;
        LocalTime a2 = start.plusMinutes(durationMinutes);
        LocalTime b1 = other.start;
        LocalTime b2 = other.start.plusMinutes(other.durationMinutes);
        return !(a2.isBefore(b1) || b2.isBefore(a1));
    }

    @Override
    public String toString() {
        return start + " for " + durationMinutes + " mins";
    }
}
