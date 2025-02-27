package commands;

import context.ExecutionContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefineTest
{

    private ExecutionContext context;
    private Define Define;

    @BeforeEach
    void setUp()
    {
        context = new ExecutionContext();
        Define = new Define();
    }

    @Test
    void testDefineValid()
    {
        assertAll(
                () -> assertDoesNotThrow(() -> Define.execute(context, List.of("x", "5"))),
                () -> assertEquals(5, context.get_param("x"))
        );
    }

    @Test
    void testDefineEmptyStack()
    {
        assertThrows(IllegalArgumentException.class, () -> Define.execute(context, List.of("x", "invalid")));
    }
}