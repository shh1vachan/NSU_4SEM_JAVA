package commands;

import context.ExecutionContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubtractTest
{

    private ExecutionContext context;
    private Subtract Subtract;

    @BeforeEach
    void setUp()
    {
        context = new ExecutionContext();
        Subtract = new Subtract();
    }

    @Test
    void testSubtractValid()
    {
        context.get_stack().push((double)5);
        context.get_stack().push((double)2);
        assertAll(
                () -> assertDoesNotThrow(() -> Subtract.execute(context, List.of())),
                () -> assertEquals(3, context.get_stack().peek())
        );
    }

    @Test
    void testSubtractInvalid()
    {
        context.get_stack().push((double)5);
        assertThrows(IllegalArgumentException.class, () -> Subtract.execute(context, List.of()));
    }
}