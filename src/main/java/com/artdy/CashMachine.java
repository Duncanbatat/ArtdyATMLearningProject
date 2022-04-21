package com.artdy;

import com.artdy.commands.CommandExecutor;

import java.util.Locale;

public class CashMachine {
    public static final String RESOURCE_PATH = "localization/";

    public static void main(String[] args) {
        try {
            Locale.setDefault(Locale.ENGLISH);
            Operation operation;

            CommandExecutor.execute(Operation.LOGIN);

            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (!operation.equals(Operation.EXIT));
        } catch (Exception e) {
            ConsoleHelper.printExitMessage();
        }
    }
}
