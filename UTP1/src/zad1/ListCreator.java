/**
 *
 *  @author Legięć Bartosz S19129
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> {
  private List<T> list;

  static public <T> ListCreator<T> collectFrom(List<T> list) {
    return new ListCreator<T>(list);
  }

  public ListCreator (List<T> list) {
    this.list = list;
  }

  public ListCreator<T> when(Selector<T> sel) {
    List<T> newList = new ArrayList<T>();

    for (T el: this.list) {
    	if (sel.select(el)) {
    		newList.add(el);	
    	}
    }

    this.list = newList;

    return this;
  }

  public <U> List<U> mapEvery(Mapper<U, T> map) {
    List<U> newList = new ArrayList<U>();

    for (T el: this.list) {
      newList.add(map.map(el));
    }

    return newList;
  }
}
