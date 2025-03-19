package commands;

import java.util.List;
import context.ExecutionContext;
import exceptions.operation.OperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PushTest
{
    private ExecutionContext context;
    private Push push;

    @BeforeEach
    void setUp()
    {
        context = new ExecutionContext();
        push = new Push();
    }

    @Test
    void testPushValid() throws OperationException {
        push.execute(context, List.of("10"));
        assertEquals(10, context.get_stack().peek());
    }

    @Test
    void testPushInvalidNumber()
    {
        assertThrows(OperationException.class, () -> {
            push.execute(context, List.of("invalid"));
        });
    }

    @Test
    void testPushVariable() throws OperationException {
        context.add_param("x", 5.0);
        push.execute(context, List.of("x"));
        assertEquals(5.0, context.get_stack().peek());
    }
}
