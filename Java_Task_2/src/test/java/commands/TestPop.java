package commands;

import context.ExecutionContext;
import exceptions.operation.OperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PopTest
{

    private ExecutionContext context;
    private Pop Pop;

    @BeforeEach
    void setUp()
    {
        context = new ExecutionContext();
        Pop = new Pop();
    }

    @Test
    void testPopValid()
    {
        context.get_stack().push((double)5);
        assertAll(
                () -> assertDoesNotThrow(() -> Pop.execute(context, List.of())),
                () -> assertTrue(context.get_stack().isEmpty())
        );
    }

    @Test
    void testPopEmptyStack()
    {
        assertThrows(OperationException.class, () -> Pop.execute(context, List.of()));
    }
}