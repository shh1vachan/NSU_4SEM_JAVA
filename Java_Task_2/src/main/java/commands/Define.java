package commands;

import context.ExecutionContext;
import exceptions.operation.OperationException;
import logger.CalcLogger;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Define implements Command
{
    private static final Logger logger = CalcLogger.getLogger();

    @Override
    public void execute(ExecutionContext context, List<String> args) throws OperationException
    {
        if (args.size() != 2)
        {
            logger.error("Define command requires exactly two arguments: <variable> <value>");
            throw new OperationException("Define command requires exactly two arguments: <variable> <value>");
        }

        String var_name = args.get(0);
        double value;
        try
        {
            value = Double.parseDouble(args.get(1));
        }
        catch (NumberFormatException e)
        {
            logger.error("Define failed. Invalid value for variable");
            throw new OperationException("Invalid value for variable '" + var_name + "'. It must be a number.");
        }

        context.add_param(var_name, value);
        logger.info("Defined: {} = {}", var_name, value);
    }
}
