package commands;

import context.ExecutionContext;
import java.util.List;

public interface Command
{
    void execute(ExecutionContext context, List<String> args) throws Exception;
}
