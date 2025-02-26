package logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalcLogger
{
    private static final Logger logger = LogManager.getLogger(CalcLogger.class);

    public static Logger getLogger()
    {
        return logger;
    }
}

