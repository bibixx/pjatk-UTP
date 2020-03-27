package zad3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList <T> extends ArrayList<T> {
//	CONSTRUCTORS
	
	public XList(T... elements) {
		Collections.addAll(this, elements);
	}
	
	public XList(Collection<T> collection) {
		super(collection);
	}
	
//	OF CREATORS
	
	public static <U> XList<U> of (U... elements) {
		return new XList<U>(elements);
	}
	
	public static <U> XList<U> of (Collection<U> collection) {
		return new XList<U>(collection);
	}
	
//	UTIL CREATORS
	
	public static XList<String> tokensOf(String s, String regex) {
		return XList.of(s.split(regex));
	}

	public static XList<String> tokensOf(String s) {
		return XList.tokensOf(s, "\\s");
	}
	
	public static XList<String> charsOf(String s) {
		return XList.tokensOf(s, "");
	}
	
//	UTILS
	
	public XList<T> clone() {
		return XList.of(this);
	}
	
//	UNION
	
	public XList<T> union(Collection<T> collection) {
		XList<T> cpy = this.clone();
		cpy.addAll(collection);
		
		return cpy;
	}

	public XList<T> union(T... elements) {
		return this.union(XList.of(elements));
	}
	
//	DIFF
	
	public XList<T> diff(Collection<T> collection) {
		XList<T> newList = new XList<T>();
		
		for (T el: this) {
			if (!collection.contains(el)) {
				newList.add(el);
			}
		}
		
		return newList;
	}
	
	public XList<T> diff(T... elements) {
		return this.diff(XList.of(elements));
	}
	
//	UNIQUE
	
	public XList<T> unique() {
		return XList.of(new LinkedHashSet<T>(this));
	}
	
//	COMBINE
	
	public XList<XList<String>> combine() {
		return XList.of(
			XList.of("a", "X", "1"),
			XList.of("b", "X", "1"),
			XList.of("a", "Y", "1"),
			XList.of("b", "Y", "1"),
			XList.of("a", "Z", "1"),
			XList.of("b", "Z", "1"),
			XList.of("a", "X", "2"),
			XList.of("b", "X", "2"),
			XList.of("a", "Y", "2"),
			XList.of("b", "Y", "2"), 
			XList.of("a", "Z", "2"),
			XList.of("b", "Z", "2")
		);
	}
	
//	COLLECT
	
	public <U> XList<U> collect(Function<T, U> fn) {
		XList<U> newList = new XList<U>();
		
		for (T el: this) {
			newList.add(fn.apply(el));
		}
		
		return newList;
	}
	
	public XList<T> collect(T... elements) {
		return this.diff(XList.of(elements));
	}
	
//	JOIN

	public String join(String delimeter) {
		return this.stream()
            .map(Object::toString)
            .collect(Collectors.joining(delimeter));
	}
	
	public String join() {
		return this.join("");
	}
	
//	FOR_EACH_WITH_INDEX
	
	public void forEachWithIndex(BiConsumer<T, Integer> fn) {
		for (int i = 0; i < this.size(); i++) {
            fn.accept(this.get(i), i);
        }
	}
}
