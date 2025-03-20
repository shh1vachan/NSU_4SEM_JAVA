package commands;

import context.ExecutionContext;
import exceptions.arithmetic.ArithmeticException;
import exceptions.operation.OperationException;
import logger.CalcLogger;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class SquareRoot implements Command
{
    private static final Logger logger = CalcLogger.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> args) throws OperationException, ArithmeticException
    {
        if (context.get_stack().isEmpty())
        {
            logger.error("Sqrt operation failed: Stack is empty.");
            throw new OperationException("Sqrt command requires at least one value in the stack.");
        }

        double value = context.get_stack().pop();

        if (value < 0)
        {
            logger.error("Sqrt operation failed: Cannot compute the square root of a negative number.");
            throw new ArithmeticException("Cannot compute the square root of a negative number.");
        }

        double result = Math.sqrt(value);
        context.get_stack().push(result);

        logger.info("Sqrt executed: sqrt({}) = {}", value, result);
    }
}
