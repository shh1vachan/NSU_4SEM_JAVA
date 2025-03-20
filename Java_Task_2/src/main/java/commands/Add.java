package commands;

import context.ExecutionContext;
import exceptions.operation.OperationException;
import logger.CalcLogger;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Add implements Command
{
    private static final Logger logger = CalcLogger.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> args) throws OperationException
    {
        if (context.get_stack().size() < 2)
        {
            logger.error("Add operation failed: insufficient arguments.");
            throw new OperationException("Add command requires at least two numbers.");
        }

        double b = context.get_stack().pop();
        double a = context.get_stack().pop();
        double result = a + b;
        context.get_stack().push(result);

        logger.info("Added: {} + {} = {}", a, b, result);
    }
}
