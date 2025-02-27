package factory;

import commands.*;
import java.util.List;
import context.ExecutionContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryTest
{

    private CommandFactory commandFactory;
    private ExecutionContext context;

    @BeforeEach
    void setUp()
    {
        commandFactory = new CommandFactory();
        context = new ExecutionContext();
    }

    @Test
    void testFactoryCreatesAlls()
    {
        assertDoesNotThrow(() -> {
            var command = commandFactory.createCommand("PUSH", context, List.of("10"));
            assertTrue(command instanceof Push);
        });

        assertDoesNotThrow(() -> {
            var command = commandFactory.createCommand("POP", context, List.of());
            assertTrue(command instanceof Pop);
        });

        assertDoesNotThrow(() -> {
            var command = commandFactory.createCommand("DEFINE", context, List.of("x", "10"));
            assertTrue(command instanceof Define);
        });

        assertDoesNotThrow(() -> {
            context.get_stack().push((double)1);
            context.get_stack().push((double)2);
            var command = commandFactory.createCommand("DIVIDE", context, List.of());
            assertTrue(command instanceof Divide);
        });

        assertDoesNotThrow(() -> {
            context.get_stack().push((double)1);
            context.get_stack().push((double)2);
            var command = commandFactory.createCommand("MULTIPLY", context, List.of());
            assertTrue(command instanceof Multiply);
        });

        assertDoesNotThrow(() -> {
            context.get_stack().push((double)1);
            context.get_stack().push((double)2);
            var command = commandFactory.createCommand("SUBTRACT", context, List.of());
            assertTrue(command instanceof Subtract);
        });

        assertDoesNotThrow(() -> {
            context.get_stack().push((double)4);
            var command = commandFactory.createCommand("SQRT", context, List.of());
            assertTrue(command instanceof SquareRoot);
        });

        assertDoesNotThrow(() -> {
            context.get_stack().push((double)1);
            context.get_stack().push((double)2);
            var command = commandFactory.createCommand("ADD", context, List.of());
            assertTrue(command instanceof Add);
        });
    }
}