package com.artdy.commands;

import com.artdy.CashMachine;
import com.artdy.ConsoleHelper;
import com.artdy.CurrencyManipulator;
import com.artdy.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean hasMoney = false;

        for (CurrencyManipulator currencyManipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (currencyManipulator.hasMoney()) {
                hasMoney = true;
                System.out.println(currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount());
            }
        }

        if (!hasMoney) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
