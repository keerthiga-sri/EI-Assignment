package com.smartoffice.commands;

import java.util.*;

public class CommandInvoker {
    private final Deque<Command> history = new ArrayDeque<>();

    public void execute(Command c) {
        c.execute();
        history.push(c);
    }
}
