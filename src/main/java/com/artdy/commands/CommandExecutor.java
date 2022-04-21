package com.artdy.commands;

import com.artdy.Operation;
import com.artdy.exceptions.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;


public class CommandExecutor {
    private static final Map<Operation, Command> allKnownCommandsMap = new HashMap() {{
        put(Operation.LOGIN, new LoginCommand());
        put(Operation.INFO, new InfoCommand());
        put(Operation.DEPOSIT, new DepositCommand());
        put(Operation.WITHDRAW, new WithdrawCommand());
        put(Operation.EXIT, new ExitCommand());
    }};

    private CommandExecutor() {
    }

    public static final void execute(Operation operation) throws InterruptOperationException {
        allKnownCommandsMap.get(operation).execute();
    }
}
