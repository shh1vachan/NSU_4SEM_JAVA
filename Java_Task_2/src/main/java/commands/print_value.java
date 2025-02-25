package commands;

import context.execution_context;
import logger.calc_logger;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class print_value implements command {
    private static final Logger logger = calc_logger.getLogger();

    @Override
    public void execute(execution_context context, List<String> args) {
        if (context.get_stack().isEmpty()) {
            logger.error("Print operation failed: Stack is empty.");
            throw new IllegalArgumentException("Print command requires at least one value in the stack.");
        }

        double topValue = context.get_stack().peek();
        System.out.println(topValue);
        logger.info("PRINT executed: Printed value {}", topValue);
    }
}
