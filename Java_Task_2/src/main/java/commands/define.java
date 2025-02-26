package commands;
import context.execution_context;
import logger.calc_logger;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class define implements command {
    private static final Logger logger = calc_logger.getLogger();

    @Override
    public void execute(execution_context context, List<String> args) {
        if (args.size() != 2) {
            logger.error("Define command requires exactly two arguments: <variable> <value>");
            throw new IllegalArgumentException("Define command requires exactly two arguments: <variable> <value>");
        }

        String var_name = args.get(0);
        double value;
        try {
            value = Double.parseDouble(args.get(1));
        } catch (NumberFormatException e) {
            logger.error("Define failed. Invalid value for variable");
            throw new IllegalArgumentException("Invalid value for variable '" + var_name + "'. It must be a number.");
        }

        context.add_param(var_name, value);
        logger.info("Defined: {} = {}", var_name, value);
    }
}
