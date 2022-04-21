package com.artdy;

import com.artdy.exceptions.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String input = null;
        try {
            input = bis.readLine();
            if (input.toUpperCase(Locale.ROOT).equals("EXIT")) {
                throw new InterruptOperationException();
            }
        } catch (IOException e) { }
        return input;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String input;
        do {
            writeMessage(res.getString("choose.currency.code"));
            input = readString();
            if (input.length() != 3) {
                writeMessage("Код валюты должен содержать 3 символа!");
                continue;
            }
            break;
        } while (true);
        return input.toUpperCase(Locale.ROOT);
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String input;
        do {
            writeMessage(res.getString("choose.denomination.and.count.format"));
            input = readString();
            if (!checkCurrencyInput(input)) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        } while (true);
        return input.split(" ");
    }

    public static String[] getValidCardNumberAndPin() throws InterruptOperationException {
        String input;
        do {
            ConsoleHelper.writeMessage("Введите номер карты и PIN:");
            input = readString();
            if (!checkCardNumberAndPinInput(input)) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        } while (true);
        return input.split(" ");
    }

    private static boolean checkCurrencyInput(String input) {
        if (input == null) return false;

        if (input.equals("")) return false;

        String[] inputArray = input.split(" ");
        if (inputArray.length != 2) return false;

        try {
            if (Integer.parseInt(inputArray[0]) < 0 || Integer.parseInt(inputArray[1]) < 0) return false;
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private static boolean checkCardNumberAndPinInput(String input) {
        if (input == null) return false;

        if (input.equals("")) return false;

        String[] inputArray = input.split(" ");
        if (inputArray.length != 2) return false;

        if (inputArray[0].length() != 12 || inputArray.length != 4) return false;

        try {
            if (Integer.parseInt(inputArray[0]) < 0 || Integer.parseInt(inputArray[1]) < 0) return false;
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.operation"));
            writeMessage("\t-1: " + res.getString("operation.INFO"));
            writeMessage("\t-2: " + res.getString("operation.DEPOSIT"));
            writeMessage("\t-3: " + res.getString("operation.WITHDRAW"));
            writeMessage("\t-4: " + res.getString("operation.EXIT"));
            String input = readString().trim();
            int operationNumber = Integer.parseInt(input);
            try {
                return Operation.getAllowableOperationByOrdinal(operationNumber);
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage() {
        writeMessage("До новых встреч!");
    }
}

