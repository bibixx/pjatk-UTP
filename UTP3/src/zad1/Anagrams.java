/**
 *
 *  @author Legięć Bartosz S19129
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.List;
import java.util.Map;

public class Anagrams {
	private List<String> wordsList;

	public Anagrams(String allWords) {
		this.wordsList = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(allWords));
		
			String line;
	
			while((line = br.readLine()) != null) {
				this.wordsList.addAll(Arrays.asList(line.split(" ")));
			}
	
			br.close();
	
			System.out.println(this.wordsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ArrayList<String>> getSortedByAnQty() {
		HashMap<TreeSet<String>, ArrayList<String>> hm = new HashMap<TreeSet<String>, ArrayList<String>>();
		
		for (String word : this.wordsList) {
			TreeSet<String> ts = new TreeSet<String>();
			ts.addAll(Arrays.asList(word.split("")));
			
			ArrayList<String> el = hm.get(ts);
			if (el == null) {
				el = new ArrayList<String>();
				hm.put(ts, el);
			}
			
			el.add(word);
		}
		
		ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();
		
		for (Map.Entry<TreeSet<String>, ArrayList<String>> entry : hm.entrySet()) {	
			out.add(entry.getValue());
		}
		
		Collections.sort(out, (el1, el2) -> el2.size() - el1.size());
		
		return out;
	}

	public String getAnagramsFor(String next) {
		return next + ": ";
	}
}  
