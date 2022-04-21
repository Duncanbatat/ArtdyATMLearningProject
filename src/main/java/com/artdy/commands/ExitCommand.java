package com.artdy.commands;

import com.artdy.CashMachine;
import com.artdy.ConsoleHelper;
import com.artdy.exceptions.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String input = ConsoleHelper.readString();
        if(input.equals("y")) {
            ConsoleHelper.writeMessage(res.getString("thank.message"));
        }
    }
}
