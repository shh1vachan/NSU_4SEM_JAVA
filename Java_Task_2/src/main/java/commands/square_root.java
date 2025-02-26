package commands;

import context.execution_context;
import logger.calc_logger;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class square_root implements command {
    private static final Logger logger = calc_logger.getLogger();

    @Override
    public void execute(execution_context context, List<String> args) {
        // Check if there are any arguments
        if (!args.isEmpty()) {
            logger.error("Sqrt operation failed: No arguments should be provided.");
            throw new IllegalArgumentException("Sqrt command does not require any arguments.");
        }

        // Pop the value from the stack
        double value = context.get_stack().pop();

        // Check if the value is negative
        if (value < 0) {
            logger.error("Sqrt operation failed: Cannot compute the square root of a negative number.");
            throw new IllegalArgumentException("Cannot compute the square root of a negative number.");
        }

        // Calculate the square root
        double result = Math.sqrt(value);

        // Push the result back onto the stack
        context.get_stack().push(result);

        // Log the successful operation
        logger.info("Sqrt executed: sqrt({}) = {}", value, result);
    }
}
