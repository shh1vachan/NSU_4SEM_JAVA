package commands;

import context.ExecutionContext;
import exceptions.operation.OperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyTest
{
    private ExecutionContext context;
    private Multiply Multiply;

    @BeforeEach
    void setUp()
    {
        context = new ExecutionContext();
        Multiply = new Multiply();
    }

    @Test
    void testMultiplyValid()
    {
        context.get_stack().push((double)5);
        context.get_stack().push((double)5);
        assertAll(
                () -> assertDoesNotThrow(() -> Multiply.execute(context, List.of())),
                () -> assertEquals(25, context.get_stack().peek())
        );
    }

    @Test
    void testMultiplyInvalid()
    {
        context.get_stack().push((double)5);
        assertThrows(OperationException.class, () -> Multiply.execute(context, List.of()));
    }
}