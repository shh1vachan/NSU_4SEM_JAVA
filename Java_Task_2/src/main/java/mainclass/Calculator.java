package mainclass;

import context.ExecutionContext;
import exceptions.factory.FactoryException;
import exceptions.operation.OperationException;
import exceptions.arithmetic.ArithmeticException;
import factory.CommandFactory;
import logger.CalcLogger;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class Calculator
{
    private static final Logger logger = CalcLogger.getLogger();
    private ExecutionContext context;

    public Calculator()
    {
        context = new ExecutionContext();
    }

    public void executeCommands(Scanner scanner, boolean isInteractive)
    {
        if (isInteractive)
        {
            System.out.println("Enter commands (type 'exit' to quit):");
        }

        while (scanner.hasNextLine())
        {
            String commandLine = scanner.nextLine();
            if ("exit".equalsIgnoreCase(commandLine.trim())) break;

            if (commandLine.startsWith("#") || commandLine.trim().isEmpty()) continue;

            try
            {
                executeCommand(commandLine);
            }
            catch (OperationException | ArithmeticException e)
            {
                logger.error("Error during command execution: ", e);
            }
        }
    }

    private void executeCommand(String commandLine) throws OperationException, ArithmeticException
    {
        String[] parts = commandLine.split("\\s+");
        String commandName = parts[0].toUpperCase();
        List<String> args = List.of(parts).subList(1, parts.length);

        try
        {
            CommandFactory factory = new CommandFactory();
            factory.createCommand(commandName, context, args);
        }
        catch (FactoryException e)
        {
            logger.error("Error creating command: ", e);
        }
    }

}
