package mainclass;

import commands.Command;
import factory.CommandFactory;
import context.ExecutionContext;
import logger.CalcLogger;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Calculator {
    private static final Logger logger = CalcLogger.getLogger();

    public void executeCommand(String commandLine, ExecutionContext context) {
        try {
            String[] parts = commandLine.split("\\s+");
            String commandName = parts[0].toUpperCase();
            List<String> args = List.of(parts).subList(1, parts.length);

            CommandFactory factory = new CommandFactory();
            factory.createCommand(commandName, context, args);

        } catch (Exception e) {
            logger.error("Error executing command: " + commandLine, e);
        }
    }
}
