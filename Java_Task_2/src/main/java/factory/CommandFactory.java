package factory;

import commands.*;
import context.ExecutionContext;
import logger.CalcLogger;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CommandFactory
{
    private static final Logger logger = CalcLogger.getLogger();

    private static final Map<String, Class<? extends Command>> commandMap = new HashMap<>();

    static
    {
        try
        {
            loadCommandsFromFile("/factory_config.txt");
        }
        catch (Exception e)
        {
            logger.error("Error initializing command factory", e);
            throw new IllegalStateException("Failed to initialize command factory", e);
        }
    }

    private static void loadCommandsFromFile(String configFilePath)
    {
        InputStream in = CommandFactory.class.getResourceAsStream(configFilePath);
        if (in == null)
        {
            logger.error("Cannot open configuration file: " + configFilePath);
            throw new IllegalArgumentException("Cannot open configuration file: " + configFilePath);
        }

        try (Scanner scanner = new Scanner(in))
        {
            while (scanner.hasNext())
            {
                String[] pair = scanner.nextLine().split("\\s*->\\s*");
                if (pair.length < 2)
                {
                    logger.error("Bad configuration file format for: " + pair);
                    throw new IllegalArgumentException("Bad configuration file format");
                }
                try
                {
                    commandMap.put(pair[0], (Class<? extends Command>) Class.forName(pair[1]));
                }
                catch (ClassNotFoundException | ClassCastException e)
                {
                    logger.error("Bad configuration file entry: " + pair, e);
                    throw new IllegalArgumentException("Bad configuration file entry: " + pair);
                }
            }
        }
    }

    public Command createCommand(String commandName, ExecutionContext context, List<String> args)
    {
        logger.info("Creating command: {}", commandName);
        Class<? extends Command> commandClass = commandMap.get(commandName.toUpperCase());

        if (commandClass == null)
        {
            logger.error("Unknown command: {}", commandName);
            throw new IllegalArgumentException("Unknown command: " + commandName);
        }
        try
        {
            Command command_ = commandClass.getDeclaredConstructor().newInstance();
            command_.execute(context, args);
            logger.info("Command {} created successfully.", commandName);
            return command_;
        }
        catch (Exception e)
        {
            logger.error("Error creating command: {}", commandName, e);
            throw new IllegalStateException("Failed to create command: " + commandName, e);
        }
    }
}