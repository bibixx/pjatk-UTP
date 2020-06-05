package zad2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TravelData {
	private List<Travel> list;

	public TravelData(File dataDir) {		
		Stream<Path> paths;
		try {
			paths = Files.walk(dataDir.toPath());
		    this.list = paths
		        .filter(Files::isRegularFile)
		        .flatMap(p -> {
					try {
						return Files.lines(p);
					} catch (IOException e) {
						e.printStackTrace();
						return null;
					}
				})
		        .map(l -> {
		        	if (l == null) {
		        		return null;
		        	}

		        	String[] arr = l.split("\t");
		        	try {
		        		return new Travel(
	        				arr[0],
	        				arr[1],
	        				arr[2],
	        				arr[3],
	        				arr[4],
	        				arr[5],
	        				arr[6]
	        			);		        		
		        	} catch (Exception e) {
		        		e.printStackTrace();
		        		return null;
		        	}
		        })
		        .filter(l -> l != null)
		        .collect(Collectors.toList()); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
		Locale l = Locale.forLanguageTag(locale.replace('_', '-'));
	
		return this.list
			.stream()
			.map(t -> t.toLocalizedString(l))
			.collect(Collectors.toList());
		
	}
	
	public List<Travel> getOffersList() {
		return this.list;
	}

}
