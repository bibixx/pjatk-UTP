package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T> {
	private T value;

	public static <U> Maybe<U> of (U value) {
		return new Maybe<U>(value);
	}
	
	public Maybe (T value) {
		this.value = value;
	}
	
	void ifPresent(Consumer<T> cons) {
		if (!this.isPresent()) {
			return;
		}
		
		cons.accept(this.value);
	}
	
	<U> Maybe<U> map(Function<T, U> func) {
		if (!this.isPresent()) {
			return Maybe.of(null);
		}
		
		return Maybe.of(func.apply(this.value));
	}
	
	T get() {
		if (!this.isPresent()) {
			throw new NoSuchElementException("maybe is empty");
		}
		
		return this.value;
	}
	
	T orElse(T defVal) {
		if (!this.isPresent()) {
			return defVal;
		}
		
		return this.value;
	}
	
	boolean isPresent() {
		return this.value != null;
	}
	
	public Maybe<T> filter (Predicate<T> predicate) {
		if (!this.isPresent() || predicate.test(this.value)) {
            return this;
        } else {
        	return Maybe.of(null);
        }
	}
	
    @Override
    public String toString() {
        if(this.isPresent())
            return "Maybe is empty";
        else
            return "Maybe has value " + this.value;
    }
}
