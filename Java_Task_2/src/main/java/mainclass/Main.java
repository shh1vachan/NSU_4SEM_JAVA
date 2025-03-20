package mainclass;

import logger.CalcLogger;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
    private static final Logger logger = CalcLogger.getLogger();

    public static void main(String[] args)
    {
        Calculator calculator = new Calculator();

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
                calculator.executeCommands(scanner, false);
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
            calculator.executeCommands(scanner, true);
        }
    }
}
