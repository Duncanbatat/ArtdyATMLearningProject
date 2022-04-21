package com.artdy.commands;

import com.artdy.CashMachine;
import com.artdy.ConsoleHelper;
import com.artdy.CurrencyManipulator;
import com.artdy.CurrencyManipulatorFactory;
import com.artdy.exceptions.InterruptOperationException;
import com.artdy.exceptions.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        
        while (true) {
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                String s = ConsoleHelper.readString();
                if (s == null) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                } else {
                    try {
                        int amount = Integer.parseInt(s);
                        boolean isAmountAvailable = currencyManipulator.isAmountAvailable(amount);
                        if (isAmountAvailable) {
                            Map<Integer, Integer> denominations = currencyManipulator.withdrawAmount(amount);
                            for (Integer item : denominations.keySet()) {
                                ConsoleHelper.writeMessage("\t" + item + " - " + denominations.get(item));
                            }

                            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, currencyCode));
                            break;
                        } else {
                            ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                        }
                    } catch (NumberFormatException e) {
                        ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    }
                }
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }
}
