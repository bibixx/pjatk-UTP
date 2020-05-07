/**
 *
 *  @author Legięć Bartosz S19129
 *
 */

package zad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) throws IOException {
	URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
	BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
	
	reader
		.lines()
		.forEach(str -> {
			char[] arr = str.toCharArray(); 
		    Arrays.sort(arr);
		    String sorted = String.valueOf(arr);

			if (map.get(sorted) == null) {
				map.put(sorted, new ArrayList<String>());
			}

			map.get(sorted).add(str);
		});
	
	int maxSize = map
		.entrySet()
		.stream()
		.max((e1, e2) -> Integer.compare(e1.getValue().size(), e2.getValue().size()))
		.get()
		.getValue()
		.size();

	map
		.entrySet()
		.stream()
		.filter(e -> e.getValue().size() == maxSize)
		.map(e -> e.getValue().stream().collect(Collectors.joining(", ")))
		.forEach(e -> System.out.println(e));
  }
}
