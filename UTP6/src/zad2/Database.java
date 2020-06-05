package zad2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class Database {
	Connection con;
	TravelData travelData;

	public Database(String dbUrl, TravelData travelData) {
		this.travelData = travelData;

		try {
			this.con = DriverManager.getConnection(dbUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void create() {
		String dropStatement = "drop table if exists travels";
		
		String crestmt = "create table travels\n" + 
				"(\n" + 
				"	travelId int auto_increment,\n" + 
				"   locale varchar(255) null,\n" +
				"	country varchar(255) null,\n" + 
				"	dateFrom date null,\n" + 
				"	dateTo date null,\n" + 
				"	place enum('lake', 'sea', 'mountains') null,\n" + 
				"	price float null,\n" + 
				"	currency char(3) null,\n" + 
				"	constraint travels_pk\n" + 
				"		primary key (travelId)\n" + 
				");\n" + 
				"\n" + 
				"";
		
		try {
			Statement statement = this.con.createStatement();
			statement.executeUpdate(dropStatement);
			statement.executeUpdate(crestmt);
			System.out.println("Table created");
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.fillDb();
	}
	
	private void fillDb() {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Statement statement = this.con.createStatement();
	
			for(Travel travel: this.travelData.getOffersList()) {
				String locale = travel.getLocale().toLanguageTag();
				String country = travel.getCountry();
				String dateFrom = df.format(travel.getStartDate());
				String dateTo = df.format(travel.getEndDate());
				String place = travel.getPlaceLabel();
				float price = travel.getPrice();
				Currency currency = travel.getCurrency();
				
				String insertQuery = "INSERT INTO travels\n" + 
						"    (locale, country, dateFrom, dateTo, place, price, currency)\n" + 
						"    VALUES\n" + 
						"    (\n" +
						"        '" + locale + "',\n" +
						"        '" + country + "',\n" + 
						"        '" + dateFrom +"',\n" + 
						"        '" + dateTo + "',\n" + 
						"        '" + place + "',\n" + 
						"        " + price + ",\n" + 
						"        '" + currency + "'\n" + 
						"    );";
	
				statement.executeUpdate(insertQuery);
			}
			
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Travels added");
	}

	private Travel[] getDataFromDb() {
		try {
			String query = "SELECT\n" + 
					"    locale, country, dateFrom, dateTo, place, price, currency\n" + 
					"FROM travels;";
			
			Statement statement;
			statement = this.con.createStatement();
			ResultSet dataSet = statement.executeQuery(query);
			ArrayList<Travel> travelList = new ArrayList<Travel>();
			
			while (dataSet.next()) {
				Locale locale = Locale.forLanguageTag(dataSet.getString("locale"));
				String country = dataSet.getString("country");
				Date startDate = dataSet.getDate("dateFrom");
				Date endDate = dataSet.getDate("dateTo");
				String place = dataSet.getString("place");
				float price = dataSet.getFloat("price");
				Currency currency = Currency.getInstance(dataSet.getString("currency"));

				travelList.add(new Travel(
					locale,
					country,
					startDate,
					endDate,
					place,
					price,
					currency
				));
			}
			
			Travel[] data = new Travel[travelList.size()];
			travelList.toArray(data);
			
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Travel[] data = {};
		return data;
	}
	
	public void showGui() {
		Travel[] data = this.getDataFromDb();

		new GUI(data);
	}
}
