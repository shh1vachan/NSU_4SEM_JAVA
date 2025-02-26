package logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class calc_logger {
    private static final Logger logger = LogManager.getLogger(calc_logger.class);

    public static Logger getLogger() {
        return logger;
    }
}

