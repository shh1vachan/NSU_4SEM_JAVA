package commands;

import java.util.List;
import context.ExecutionContext;
import exceptions.arithmetic.ArithmeticException;
import exceptions.stack.StackUnderflowException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrtTest
{
    private ExecutionContext context;
    private SquareRoot squareRoot;

    @BeforeEach
    void setUp()
    {
        context = new ExecutionContext();
        squareRoot = new SquareRoot();
    }

    @Test
    void testSquareRootValid() throws StackUnderflowException, ArithmeticException {
        context.get_stack().push(9.0);
        squareRoot.execute(context, List.of());
        assertEquals(3.0, context.get_stack().peek());
    }

    @Test
    void testSquareRootEmptyStack()
    {
        assertThrows(StackUnderflowException.class, () -> {
            squareRoot.execute(context, List.of());
        });
    }

    @Test
    void testSquareRootInvalid()
    {
        context.get_stack().push(-9.0);
        assertThrows(ArithmeticException.class, () -> {
            squareRoot.execute(context, List.of());
        });
    }
}
