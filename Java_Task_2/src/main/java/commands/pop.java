package commands;
import context.execution_context;
import logger.calc_logger;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class pop implements command {
    private static final Logger logger = calc_logger.getLogger();

    @Override
    public void execute(execution_context context, List<String> args) {
        if (context.get_stack().isEmpty()) {
            logger.error("Pop failed. Pop command requires at least one value in the stack.");
            throw new IllegalArgumentException("Pop command requires at least one value in the stack.");
        }
        double pop_value = context.get_stack().pop();
        logger.info("Popped: ", pop_value);
    }
}
