package commands;
import context.execution_context;
import logger.calc_logger;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class add implements command {
    private static final Logger logger = calc_logger.getLogger();

    @Override
    public void execute(execution_context context, List<String> args) {
        if (context.get_stack().size() < 2) {
            logger.error("Add operation failed: Not enough elements in stack.");
            throw new IllegalArgumentException("Add command requires at least two values in the stack.");
        }

        double second = context.get_stack().pop();
        double first = context.get_stack().pop();

        context.get_stack().push(first + second);
        logger.info("ADD executed: {} + {} = {}", first, second, first + second);
    }
}
