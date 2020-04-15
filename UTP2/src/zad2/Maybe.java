package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T> {
    T value;
    public Maybe(){}
    public Maybe(T value){
        this.value = value;
    }
    public static<R> Maybe<R> of(R value){
        return new Maybe(value);
    }
    public void ifPresent(Consumer cons){
        if(value != null)
            cons.accept(value);
    }
    public <R> Maybe<R> map(Function<T,R> func) {
        return new Maybe((R)func.apply(value));
    }
    public T get() throws NoSuchElementException {
        if(value == null)
            new NoSuchElementException();
        return value;
    }
    public boolean isPresent(){
        return value != null;
    }

    public T orElse(T defVal) {
        if(value == null)
            return defVal;
        else
            return value;
    }
    public Maybe<T> filter (Predicate<T> pred) {
        if(pred.test((T)this.value) || value == null)
            return this;
        else
            return new Maybe();
    }
}