/**
 *
 *  @author Legięć Bartosz S19129
 *
 */

package zad2;

public class Purchase {
	public String id;
	public String surname;
	private String firstname;
	private String product;
	private float price;
	private float quantity;
	
	public Purchase(String line) {
		String[] parts = line.split(";");
		
		this.id = parts[0];
		
		String[] nameParts = parts[1].split(" ");
		
		this.surname = nameParts[0];
		this.firstname = nameParts[1];
		
		this.product = parts[2];
		
		this.price = Float.parseFloat(parts[3]);
		this.quantity = Float.parseFloat(parts[4]);
	}
	
	public float getCost() {
		return this.price * this.quantity;
	}
	
	public String toString(boolean withCost) {
		String joined = String.join(
			";",
			this.id,
			this.surname + " " + this.firstname,
			this.product,
			Float.toString(this.price),
			Float.toString(this.quantity)
		);
		
		if (!withCost) {
			return joined;
		}
		
		String costAsString = Float.toString(this.getCost()); 
		return joined + " (koszt: " + costAsString + ")";
	}
	

	public String toString() {
		return this.toString(false);
	}
}
