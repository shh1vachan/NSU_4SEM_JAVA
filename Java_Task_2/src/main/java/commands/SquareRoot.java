package commands;

import context.ExecutionContext;
import logger.CalcLogger;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class SquareRoot implements Command
{
    private static final Logger logger = CalcLogger.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> args)
    {
        if (!args.isEmpty())
        {
            logger.error("Sqrt operation failed: No arguments should be provided.");
            throw new IllegalArgumentException("Sqrt command does not require any arguments.");
        }

        double value = context.get_stack().pop();

        if (value < 0)
        {
            logger.error("Sqrt operation failed: Cannot compute the square root of a negative number.");
            throw new IllegalArgumentException("Cannot compute the square root of a negative number.");
        }

        double result = Math.sqrt(value);
        context.get_stack().push(result);

        logger.info("Sqrt executed: sqrt({}) = {}", value, result);
    }
}
