package com.artdy.commands;

import com.artdy.CashMachine;
import com.artdy.exceptions.InterruptOperationException;

import java.util.ResourceBundle;

public class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit");
    @Override
    public void execute() throws InterruptOperationException {

    }
}
