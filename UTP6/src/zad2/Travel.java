package zad2;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Travel {
	private Locale locale;
	private String country;
	private Date startDate;
	private Date endDate;
	private String placeLabel;
	private float price;
	private String currency;

	public Travel(
		String locale,
		String country,
		String startDate,
		String endDate,
		String place,
		String price,
		String currency
	) {
		Locale l = Locale.forLanguageTag(locale.replace('_', '-'));

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		NumberFormat nf = NumberFormat.getInstance(l);
		
	    try {
	    	this.locale = l;
		    this.country = CountryTranslator.translate(country, l, Locale.ENGLISH);
			this.startDate = df.parse(startDate);
			this.endDate = df.parse(endDate);
			this.placeLabel = this.getPlaceLabel(place, l);
		    this.price = nf.parse(price).floatValue();
		    this.currency = currency;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private String getPlaceLabel(String place, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		String[] labels = { "mountains", "sea", "lake" };

		for (String label: labels) {
			if (place.equals(bundle.getString(label))) {
				return label;
			}
		}
		
		return "";
	}
	
	public String toLocalizedString(Locale locale, String dateFromat) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		SimpleDateFormat df = new SimpleDateFormat(dateFromat);
		NumberFormat nf = NumberFormat.getInstance(locale);

		String country = CountryTranslator.translate(this.country, Locale.ENGLISH, locale);
		String startDate = df.format(this.startDate);
		String endDate = df.format(this.endDate);
		String place = bundle.getString(this.placeLabel);
		String price = nf.format(this.price); 

		return country + ' ' + startDate + ' ' + endDate + ' ' + place + ' ' + price + ' ' + this.currency;
	}
}
