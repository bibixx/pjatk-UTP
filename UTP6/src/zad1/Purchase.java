/**
 *
 *  @author Legięć Bartosz S19129
 *
 */

package zad1;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

public class Purchase {
	private String prod;
	private String data;
	private Double price;
	
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
	private VetoableChangeSupport vetoSupport = new VetoableChangeSupport(this);
	
	public Purchase(String prod, String data, double price) {
		this.prod = prod;
		this.data = data;
		this.price = price;
	}

	public synchronized void setData(String newData) {
		changeSupport.firePropertyChange("data", this.data, newData);
		this.data = newData;
	}

	public synchronized void setPrice(double newPrice) throws PropertyVetoException {
		vetoSupport.fireVetoableChange("price", this.price, newPrice);
		changeSupport.firePropertyChange("price", this.price, newPrice);
		this.price = newPrice;
	}
	
	public void setProd(String prod) {
		this.prod = prod;
	}
	
    public String getData() {
        return this.data;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getProd() {
        return this.prod;
    }
    
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
    	changeSupport.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
    	changeSupport.removePropertyChangeListener(listener);
    }
    
    public synchronized void addVetoableChangeListener(VetoableChangeListener listener) {
    	vetoSupport.addVetoableChangeListener(listener);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener listener) {
    	vetoSupport.removeVetoableChangeListener(listener);
    }
    
    public String toString() {
    	return "Purchase [prod=" + this.getProd() + ", data=" + this.getData() + ", price=" + this.getPrice() + "]";
    }
}  
