package commands;

import context.execution_context;
import java.util.List;

public interface command
{
    void execute(execution_context context, List<String> args) throws Exception;
}
