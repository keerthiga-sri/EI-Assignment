package com.smartoffice.commands;

import com.smartoffice.Office;

public class ConfigureRoomsCommand implements Command {
    private final int n;
    public ConfigureRoomsCommand(int n) { this.n = n; }
    @Override
    public void execute() {
        Office.getInstance().configureRooms(n);
    }
}
