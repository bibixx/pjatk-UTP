/**
 *
 *  @author Legięć Bartosz S19129
 *
 */

package zad2;


import java.util.*;

public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations)
       .when((String el) -> el.startsWith("WAW"))
       .mapEvery((String el) -> {
    	   String[] l = el.split(" ");
    	   Integer price = Integer.parseInt(l[2]);
    	   Integer priceInPln = (int) (price * xrate);
    	   
    	   return String.format("to %s - price in PLN:	%d", l[1], priceInPln);
       });
  }

  public static void main(String[] args) {
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
