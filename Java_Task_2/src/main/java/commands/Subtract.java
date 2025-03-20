package commands;
import context.ExecutionContext;
import logger.CalcLogger;
import org.apache.logging.log4j.Logger;
import java.util.List;
import exceptions.operation.OperationException;

public class Subtract implements Command
{
    private static final Logger logger = CalcLogger.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> args) throws OperationException
    {
        if (context.get_stack().size() < 2)
        {
            logger.error("Subtract operation failed: Not enough elements in stack.");
            throw new OperationException("Subtract command requires at least two values in the stack.");
        }

        double second = context.get_stack().pop();
        double first = context.get_stack().pop();

        context.get_stack().push(first - second);
        logger.info("Subtract executed: {} - {} = {}", first, second, first - second);
    }
}
