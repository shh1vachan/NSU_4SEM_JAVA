package mainclass;

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
        Calculator calculator = new Calculator();  // Создаем объект калькулятора

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
                        continue; // for empty strings
                    }
                    try
                    {
                        calculator.executeCommand(commandLine, context);  // Вызов метода калькулятора
                    }
                    catch (Exception e)
                    {
                        logger.error("Error during command execution: ", e);
                    }
                }
            }
            catch (FileNotFoundException e)
            {
                logger.error("File not found: " + filename, e);
                System.exit(1);
            }
        }
        else
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter commands (type 'exit' to quit calculator):");
            while (scanner.hasNextLine())
            {
                String commandLine = scanner.nextLine();
                if ("exit".equalsIgnoreCase(commandLine.trim()))
                    break;
                if (commandLine.startsWith("#") || commandLine.trim().isEmpty())
                    continue; // for comments and empty strings

                try
                {
                    calculator.executeCommand(commandLine, context);  // Вызов метода калькулятора
                }
                catch (Exception e)
                {
                    logger.error("Error during command execution: ", e);
                }
            }
        }
    }
}
