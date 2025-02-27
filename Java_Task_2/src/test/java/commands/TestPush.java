package commands;

import context.ExecutionContext;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PushTest
{

    private ExecutionContext context;
    private Push Push;

    @BeforeEach
    void setUp()
    {
        context = new ExecutionContext();
        Push = new Push();
    }

    @Test
    void testPushValid()
    {
        Push.execute(context, List.of("10"));
        assertEquals(10, context.get_stack().peek());
    }

    @Test
    void testPushInvalidNumber()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            Push.execute(context, List.of("invalid"));
        });
    }

    @Test
    void testPushVariable()
    {
        context.add_param("x", (double)5);
        Push.execute(context, List.of("x"));
        assertEquals(5, context.get_stack().peek());
    }
}