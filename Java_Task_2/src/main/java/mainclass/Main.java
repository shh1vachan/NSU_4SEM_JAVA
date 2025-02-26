package mainclass;

import factory.CommandFactory;
import context.ExecutionContext;
import logger.CalcLogger;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;


public class Main 
{
    private static final Logger logger = CalcLogger.getLogger();

    public static void main(String[] args)
    {
        ExecutionContext context = new ExecutionContext();

        if (args.length > 0)
        {
            String filename = args[0];
            File file = new File(filename);
            logger.info("Looking for file: {}", file.getAbsolutePath());
            if (!file.exists() || !file.canRead())
            {
                logger.error("Cannot read file: " + filename);
                System.exit(1);
            }
            try (Scanner scanner = new Scanner(file))
            {
                while (scanner.hasNextLine())
                {
                    String commandLine = scanner.nextLine();
                    if (commandLine.startsWith("#") || commandLine.trim().isEmpty())
                    {
                        continue; //for empty strings
                    }
                    executeCommand(commandLine, context);
                }
            } catch (FileNotFoundException e)
            {
                logger.error("File not found: " + filename, e);
                System.exit(1);
            }
        }
            
        else
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter commands (type 'exit' to quit):");
            while (scanner.hasNextLine())
            {
                String commandLine = scanner.nextLine();
                if ("exit".equalsIgnoreCase(commandLine.trim()))
                    break;
                if (commandLine.startsWith("#") || commandLine.trim().isEmpty())
                    continue; // for comments and empty strings

                executeCommand(commandLine, context);
            }
        }
    }

    private static void executeCommand(String commandLine, ExecutionContext context)
    {
        try
        {
            String[] parts = commandLine.split("\\s+");
            String commandName = parts[0].toUpperCase();
            List<String> args = List.of(parts).subList(1, parts.length);

            CommandFactory factory = new CommandFactory();
            factory.createCommand(commandName, context, args);

        }
        catch (Exception e)
        {
            logger.error("Error executing command: " + commandLine, e);
        }
    }
}
