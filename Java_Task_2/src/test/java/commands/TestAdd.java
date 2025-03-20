package commands;

import context.ExecutionContext;
import exceptions.operation.OperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddTest
{

    private ExecutionContext context;
    private Add Add;

    @BeforeEach
    void setUp()
    {
        context = new ExecutionContext();
        Add = new Add();
    }

    @Test
    void testAddValid()
    {
        context.get_stack().push((double)5);
        context.get_stack().push((double)2);
        assertAll(
                () -> assertDoesNotThrow(() -> Add.execute(context, List.of())),
                () -> assertEquals(7, context.get_stack().peek())
        );
    }

    @Test
    void testAddInvalid()
    {
        context.get_stack().push((double)5);
        assertThrows(OperationException.class, () -> Add.execute(context, List.of()));
    }
}