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
import java.util.stream.Collectors;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean areWordsAnagrams(String w1, String w2) {
		TreeSet<String> set1 = new TreeSet<String>();
		TreeSet<String> set2 = new TreeSet<String>();
		
		set1.addAll(Arrays.asList(w1.split("")));
		set2.addAll(Arrays.asList(w2.split("")));
		
		return set1.equals(set2);
	}

	public List<ArrayList<String>> getSortedByAnQty() {
		ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();

		for (String word : this.wordsList) {
			boolean found = false;

			for (ArrayList<String> list: out) {
				String listWord = list.get(0);
				
				if (this.areWordsAnagrams(listWord, word)) {
					list.add(word);
					found = true;
					break;
				}
			}
			
			if (!found) {
				ArrayList<String> newList = new ArrayList<String>();
				newList.add(word);
				out.add(newList);
			}
		}
		
		return out
			.stream()
			.sorted((el1, el2) -> {
				int diff = el2.size() - el1.size();
				
				if (diff == 0) {
					return el1.get(0).compareTo(el2.get(0));
				}
				
				return diff;
			})
			.collect(Collectors.toList());
	}

	public String getAnagramsFor(String searchedWord) {
		ArrayList<String> l = this
			.getSortedByAnQty()
			.stream()
			.filter((el) -> this.areWordsAnagrams(searchedWord, el.get(0)))
			.findAny()
		    .orElse(null);
		
		if (l == null) {
			return searchedWord + ": null";			
		}
		
		List<String> lWithoutSearched = l
			.stream()
			.filter(el -> !el.equals(searchedWord))
			.collect(Collectors.toList());
		
		return searchedWord + ": " + lWithoutSearched;
	}
}  
