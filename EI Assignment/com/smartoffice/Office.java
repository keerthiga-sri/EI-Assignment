package com.smartoffice;

import java.util.*;
import java.util.concurrent.*;

public class Office {
    private static Office instance;
    private final Map<Integer, Room> rooms = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    private Office() {}

    public static synchronized Office getInstance() {
        if (instance == null) instance = new Office();
        return instance;
    }

    public synchronized void configureRooms(int n) {
        rooms.clear();
        for (int i = 1; i <= n; i++) rooms.put(i, new Room(i));
        System.out.println("Office configured with " + n + " meeting rooms: " + rooms.keySet());
    }

    public Room getRoom(int id) {
        return rooms.get(id);
    }

    public String getRoomStatusReport(int id) {
        Room r = rooms.get(id);
        if (r == null) return "Invalid room number. Please enter a valid room number.";
        return r.statusReport();
    }

    public String summary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Office Summary:\\n");
        rooms.values().forEach(r -> sb.append(r.summaryLine()).append("\\n"));
        return sb.toString();
    }

    public void schedule(Runnable r, long delayMs) {
        scheduler.schedule(r, delayMs, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        scheduler.shutdownNow();
    }
}
