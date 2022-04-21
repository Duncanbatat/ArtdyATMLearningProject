package com.artdy.commands;

import com.artdy.CashMachine;
import com.artdy.ConsoleHelper;
import com.artdy.exceptions.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String cardNumber = ConsoleHelper.readString();
            String cardPin = ConsoleHelper.readString();

            if (cardNumber.length() != 12 || cardPin.length() != 4) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            } else {
                if (validCreditCards.containsKey(cardNumber) && validCreditCards.getString(cardNumber).equals(cardPin)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cardNumber));
                    break;
                } else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cardNumber));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            }
        }
    }
}
