package zad2;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

public class DbTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private Travel[] data;
	private String[] columnNames;
	private ResourceBundle bundle;
	private Locale locale;
	
	public DbTableModel(Travel[] data, Locale locale) {
		this.data = data;
		this.bundle = ResourceBundle.getBundle("messages", locale);
		this.locale = locale;
		
		String[] columnNames = {
			"country",
			"dateFrom",
			"dateTo",
			"place",
			"price"
        };
		
		this.columnNames = columnNames;
	}

	@Override
	public int getRowCount() {
		return this.data.length;
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String columnName = this.columnNames[columnIndex];
		Travel row = this.data[rowIndex];
		
		switch (columnName) {
			case "country": {
				return row.getCountry(this.locale);
			}
			case "dateFrom": {
				return row.getStartDate(this.locale);
			}
			case "dateTo": {
				return row.getEndDate(this.locale);
			}
			case "place": {
				return row.getPlaceLabel(this.locale);
			}
			case "price": {
				return row.getPrice(this.locale);
			}
			default: {
				return "";
			}
		}
	}
	
	public String getColumnName(int column) {
	    if (this.columnNames[column] != null) {
	    	return this.bundle.getString(this.columnNames[column]);
	    }
	    
	    return "";
	}

}
