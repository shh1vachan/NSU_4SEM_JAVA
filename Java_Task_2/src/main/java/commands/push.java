package commands;
import context.execution_context;
import logger.calc_logger;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class push implements command {
    private static final Logger logger = calc_logger.getLogger();

    @Override
    public void execute(execution_context context, List<String> args) {
        if (args.isEmpty()) {
            logger.error("Push operation failed: no arguments.");
            throw new IllegalArgumentException("Push command requires 1 argument.");
        }

        String arg = args.getFirst();
        try {
            double value = Double.parseDouble(arg);
            context.get_stack().push(value);
            System.out.println("Pushed: " + value);
        } catch (NumberFormatException e) {
            if (!context.has_param(arg)) {
                logger.error("Push failed: no parameters", arg);
                throw new IllegalArgumentException("Error: '" + arg + "' is not a number or a defined variable.");
            }
            double value = context.get_param(arg);
            context.get_stack().push(value);
            context.get_stack().push(value);
            logger.info("Pushed variable: " + arg + " with value " + value);
        }
    }
}
