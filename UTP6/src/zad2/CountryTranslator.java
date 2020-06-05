package zad2;

import java.util.Locale;

public class CountryTranslator {
	static String translate(String country, Locale localeFrom, Locale localeTo) {
		Locale[] allLocales = Locale.getAvailableLocales();
		
		for (int i = 0; i < allLocales.length; i++) {
			Locale locale = allLocales[i];
			
			if (locale.getDisplayCountry(localeFrom).equals(country)) {
				return locale.getDisplayCountry(localeTo);
			}
		}
		
		return "";
	}
}
