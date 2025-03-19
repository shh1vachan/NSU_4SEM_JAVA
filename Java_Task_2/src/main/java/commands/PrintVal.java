package commands;

import context.ExecutionContext;
import exceptions.stack.StackUnderflowException;
import logger.CalcLogger;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class PrintVal implements Command
{
    private static final Logger logger = CalcLogger.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> args) throws StackUnderflowException
    {
        if (context.get_stack().isEmpty())
        {
            logger.error("Print operation failed: Stack is empty.");
            throw new StackUnderflowException("Print command requires at least one value in the stack.");
        }

        double topValue = context.get_stack().peek();
        System.out.println(topValue);
        logger.info("PRINT executed: Printed value {}", topValue);
    }
}
