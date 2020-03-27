package zad1;

import java.util.function.Function;

public class InputConverter <T> {
    private T initialInput;

    public InputConverter(T t) {
        this.initialInput = t;
    }

    public <U> U convertBy(Function... functions) {
        Object input = this.initialInput;
        Object result = null;

        for (Function f : functions) {
            result = f.apply(input);
            input = result;
        }

        return (U) result;
    }
}
