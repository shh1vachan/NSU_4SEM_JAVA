package commands;

import context.ExecutionContext;
import exceptions.operation.OperationException;
import exceptions.arithmetic.ArithmeticException;

import java.util.List;

public interface Command
{
    void execute(ExecutionContext context, List<String> args) throws OperationException, ArithmeticException;
}
