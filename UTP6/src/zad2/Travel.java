package zad2;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
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
	private Currency currency;

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
		    this.currency = Currency.getInstance(currency);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public Travel(
	    Locale locale,
	    String country,
	    Date startDate,
	    Date endDate,
	    String placeLabel,
	    float price,
	    Currency currency
	) {
	    this.locale = locale;
	    this.country = country;
	    this.startDate = startDate;
	    this.endDate = endDate;
	    this.placeLabel = placeLabel;
	    this.price = price;
	    this.currency = currency;
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
	
	public String toLocalizedString(Locale localedateFromat) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		NumberFormat nf = NumberFormat.getInstance(locale);

		String country = CountryTranslator.translate(this.country, Locale.ENGLISH, locale);
		String startDate = df.format(this.startDate);
		String endDate = df.format(this.endDate);
		String place = bundle.getString(this.placeLabel);
		String price = nf.format(this.price);

		return country + ' ' + startDate + ' ' + endDate + ' ' + place + ' ' + price + ' ' + this.currency;
	}
	
	public Locale getLocale() {
	  return this.locale;
	}
	
	public String getCountry() {
	  return this.country;
	}
	
	public String getCountry(Locale locale) {
	  return CountryTranslator.translate(this.country, Locale.ENGLISH, locale);
	}
	
	public Date getStartDate() {
	  return this.startDate;
	}
	
	public String getStartDate(Locale locale) {
		DateFormat fd = DateFormat.getDateInstance(DateFormat.SHORT, locale);
		return fd.format(this.startDate);
	}
	
	public Date getEndDate() {
	  return this.endDate;
	}

	public String getEndDate(Locale locale) {
		DateFormat fd = DateFormat.getDateInstance(DateFormat.SHORT, locale);
		return fd.format(this.endDate);
	}
	
	public String getPlaceLabel() {
	  return this.placeLabel;
	}
	
	public String getPlaceLabel(Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
	    return bundle.getString(this.placeLabel);
	}
	
	public float getPrice() {
	  return this.price;
	}
	
	public String getPrice(Locale locale) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		nf.setCurrency(this.currency);

		return nf.format(this.price);
	}
	
	public Currency getCurrency() {
	  return this.currency;
	}
}
