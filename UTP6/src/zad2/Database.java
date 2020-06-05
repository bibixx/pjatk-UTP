package zad2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Currency;

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
				String country = travel.getCountry();
				String dateFrom = df.format(travel.getStartDate());
				String dateTo = df.format(travel.getEndDate());
				String place = travel.getPlaceLabel();
				float price = travel.getPrice();
				Currency currency = travel.getCurrency();
				
				String insertQuery = "INSERT INTO travels\n" + 
						"    (country, dateFrom, dateTo, place, price, currency)\n" + 
						"    VALUES\n" + 
						"    (\n" + 
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

	public void showGui() {
		// TODO Auto-generated method stub
		
	}
}
