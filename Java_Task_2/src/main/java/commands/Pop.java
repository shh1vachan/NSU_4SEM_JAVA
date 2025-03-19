package commands;

import context.ExecutionContext;
import exceptions.stack.StackUnderflowException;
import logger.CalcLogger;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Pop implements Command
{
    private static final Logger logger = CalcLogger.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> args) throws StackUnderflowException
    {
        if (context.get_stack().isEmpty())
        {
            logger.error("Pop failed. Pop command requires at least one value in the stack.");
            throw new StackUnderflowException("Pop command requires at least one value in the stack.");
        }

        double pop_value = context.get_stack().pop();
        logger.info("Popped: {}", pop_value);
    }
}
