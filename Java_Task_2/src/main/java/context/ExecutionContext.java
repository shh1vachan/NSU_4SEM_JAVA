package context;

import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

public class ExecutionContext
{
    private Stack<Double> stack;
    private Map<String, Double> parameters;

    public ExecutionContext()
    {
        this.stack = new Stack<>();
        this.parameters = new HashMap<>();
    }

    public Stack<Double> get_stack()
    {
        return stack;
    }

    public Map<String, Double> get_params()
    {
        return parameters;
    }

    public void add_param(String name, Double value)
    {
        parameters.put(name, value);
    }

    public Double get_param(String name)
    {
        return parameters.get(name);
    }

    public boolean has_param(String name)
    {
        return parameters.containsKey(name);
    }

    public void clear_stack()
    {
        stack.clear();
    }

    public void clear_params()
    {
        parameters.clear();
    }
}
