package commands;

import context.ExecutionContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SqrtTest
{
    private ExecutionContext context;
    private SquareRoot SquareRoot;

    @BeforeEach
    void setUp()
    {
        context = new ExecutionContext();
        SquareRoot = new SquareRoot();
    }

    @Test
    void testSquareRootValid()
    {
        context.get_stack().push((double)16);
        assertAll(
                () -> assertDoesNotThrow(() -> SquareRoot.execute(context, List.of())),
                () -> assertEquals(4, context.get_stack().peek())
        );
    }

    @Test
    void testSquareRootInvalid()
    {
        assertThrows(EmptyStackException.class, () -> SquareRoot.execute(context, List.of()));
    }
}