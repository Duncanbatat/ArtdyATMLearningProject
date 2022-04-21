package com.artdy.commands;

import com.artdy.exceptions.InterruptOperationException;

public interface Command {
    void execute() throws InterruptOperationException;
}
