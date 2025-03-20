package commands;

import context.ExecutionContext;
import exceptions.operation.OperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DivideTest
{
    private ExecutionContext context;
    private Divide Divide;

    @BeforeEach
    void setUp()
    {
        context = new ExecutionContext();
        Divide = new Divide();
    }

    @Test
    void testDivideValid()
    {
        context.get_stack().push((double)6);
        context.get_stack().push((double)2);
        assertAll(
                () -> assertDoesNotThrow(() -> Divide.execute(context, List.of())),
                () -> assertEquals(3, context.get_stack().peek())
        );
    }

    @Test
    void testDivideInvalid()
    {
        context.get_stack().push((double)5);
        assertThrows(OperationException.class, () -> Divide.execute(context, List.of()));
    }
}