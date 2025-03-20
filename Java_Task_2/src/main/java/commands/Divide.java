package commands;

import context.ExecutionContext;
import exceptions.arithmetic.DivisionByZeroException;
import exceptions.operation.OperationException;
import logger.CalcLogger;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Divide implements Command
{
    private static final Logger logger = CalcLogger.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> args) throws OperationException, DivisionByZeroException
    {
        if (context.get_stack().size() < 2)
        {
            logger.error("Divide operation failed: Not enough elements in stack.");
            throw new OperationException("Divide command requires at least two values in the stack.");
        }

        double second = context.get_stack().pop();
        double first = context.get_stack().pop();

        if (second == 0)
        {
            logger.error("Divide operation failed: Division by zero.");
            context.get_stack().push(first);
            throw new DivisionByZeroException("Cannot divide by zero.");
        }

        context.get_stack().push(first / second);
        logger.info("Divide executed: {} / {} = {}", first, second, first / second);
    }
}
