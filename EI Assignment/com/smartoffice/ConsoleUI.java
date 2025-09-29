package com.smartoffice;

import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.smartoffice.commands.ConfigureRoomsCommand;
import com.smartoffice.commands.UpdateOccupancyCommand;
import com.smartoffice.commands.SetRoomCapacityCommand;
import com.smartoffice.commands.BookRoomCommand;
import com.smartoffice.commands.CancelBookingCommand;
import com.smartoffice.commands.CommandInvoker;

public class ConsoleUI {
    private final Scanner sc = new Scanner(System.in);
    private final Office office = Office.getInstance();
    private final CommandInvoker invoker = new CommandInvoker();

    public void start() {
        System.out.println("Smart Office Console. Type 'help' for commands.");
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            String cmd = parts[0].toLowerCase();
            try {
                switch (cmd) {
                    case "help":
                        printHelp();
                        break;
                    case "config":
                        if (parts.length == 3 && parts[1].equalsIgnoreCase("rooms")) {
                            int n = Integer.parseInt(parts[2]);
                            invoker.execute(new ConfigureRoomsCommand(n));
                        } else if (parts.length == 5 && parts[1].equalsIgnoreCase("room") && parts[2].equalsIgnoreCase("max")) {
                            int roomId = Integer.parseInt(parts[3]);
                            int cap = Integer.parseInt(parts[4]);
                            invoker.execute(new SetRoomCapacityCommand(roomId, cap));
                        } else {
                            System.out.println("Invalid config command. See help.");
                        }
                        break;
                    case "block":
                    case "book":
                        if (parts.length == 4) {
                            int roomId = Integer.parseInt(parts[1]);
                            LocalTime t = LocalTime.parse(parts[2], DateTimeFormatter.ofPattern("HH:mm"));
                            int dur = Integer.parseInt(parts[3]);
                            invoker.execute(new BookRoomCommand(roomId, t, dur));
                        } else {
                            System.out.println("Usage: block <roomId> HH:mm <durationMinutes>");
                        }
                        break;
                    case "cancel":
                        if (parts.length == 2) {
                            int roomId = Integer.parseInt(parts[1]);
                            invoker.execute(new CancelBookingCommand(roomId));
                        } else {
                            System.out.println("Usage: cancel <roomId>");
                        }
                        break;
                    case "add":
                        if (parts.length == 4 && parts[1].equalsIgnoreCase("occupant")) {
                            int roomId = Integer.parseInt(parts[2]);
                            int cnt = Integer.parseInt(parts[3]);
                            invoker.execute(new UpdateOccupancyCommand(roomId, cnt));
                        } else {
                            System.out.println("Usage: add occupant <roomId> <count>");
                        }
                        break;
                    case "status":
                        if (parts.length == 2) {
                            int roomId = Integer.parseInt(parts[1]);
                            System.out.println(office.getRoomStatusReport(roomId));
                        } else {
                            System.out.println(office.summary());
                        }
                        break;
                    case "exit":
                    case "quit":
                        System.out.println("Exiting.");
                        office.shutdown();
                        return;
                    default:
                        System.out.println("Unknown command. Type 'help'.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void printHelp() {
        String help =
            "Commands:\n" +
            "  config rooms <n>                        - Configure number of rooms\n" +
            "  config room max <roomId> <capacity>    - Set room capacity\n" +
            "  block/book <roomId> HH:mm <duration>   - Book a room\n" +
            "  cancel <roomId>                        - Cancel booking\n" +
            "  add occupant <roomId> <count>          - Update occupant count\n" +
            "  status [roomId]                        - Show status or summary\n" +
            "  help | exit\n";
        System.out.println(help);
    }
}
