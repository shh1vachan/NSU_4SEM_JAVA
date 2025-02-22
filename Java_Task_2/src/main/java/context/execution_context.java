package context;

import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

public class execution_context
{
    private Stack<Double> stack; // Стек для хранения чисел
    private Map<String, Double> parameters; // Параметры, заданные через команду DEFINE

    public execution_context() {
        this.stack = new Stack<>(); // Инициализация стека
        this.parameters = new HashMap<>(); // Инициализация карты параметров
    }

    // Получение стека
    public Stack<Double> get_stack() {
        return stack;
    }

    // Получение параметров
    public Map<String, Double> get_params() {
        return parameters;
    }

    // Добавление параметра в Map
    public void add_param(String name, Double value) {
        parameters.put(name, value);
    }

    // Получение значения параметра по имени
    public Double get_param(String name) {
        return parameters.get(name);
    }

    // Проверка, существует ли параметр
    public boolean has_param(String name) {
        return parameters.containsKey(name);
    }

    // Метод для очистки стека
    public void clear_stack() {
        stack.clear();
    }

    // Метод для очистки всех параметров
    public void clear_params() {
        parameters.clear();
    }
}
