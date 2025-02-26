package commands;
import context.execution_context;
import logger.calc_logger;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class subtract implements command {
    private static final Logger logger = calc_logger.getLogger();

    @Override
    public void execute(execution_context context, List<String> args) {
        if (context.get_stack().size() < 2) {
            logger.error("Subtract operation failed: Not enough elements in stack.");
            throw new IllegalArgumentException("Subtract command requires at least two values in the stack.");
        }

        double b = context.get_stack().pop();
        double a = context.get_stack().pop();
        double result = a - b;

        context.get_stack().push(result);
        logger.info("Subtract executed: {} + {} = {}", a, b, result);
    }
}
