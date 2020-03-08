/**
 *
 *  @author Legięć Bartosz S19129
 *
 */

package zad2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ListCreator<T> {
  private List<T> list;

  static public <T> ListCreator<T> collectFrom(List<T> list) {
    return new ListCreator<T>(list);
  }

  public ListCreator (List<T> list) {
    this.list = list;
  }

  public ListCreator<T> when(Function<T, Boolean> sel) {
    List<T> newList = new ArrayList<T>();

    for (T el: this.list) {
    	if (sel.apply(el)) {
    		newList.add(el);	
    	}
    }

    this.list = newList;

    return this;
  }

  public <U> List<U> mapEvery(Function<T, U> map) {
    List<U> newList = new ArrayList<U>();

    for (T el: this.list) {
      newList.add(map.apply(el));
    }

    return newList;
  }
}
