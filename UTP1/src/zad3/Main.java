/**
 *
 *  @author Legięć Bartosz S19129
 *
 */

package zad3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest
    	.stream()
    	.filter((String el) -> el.startsWith("WAW"))
        .map((String el) -> {
     	   String[] l = el.split(" ");
     	   Integer price = Integer.parseInt(l[2]);
     	   Integer priceInPln = (int) (price * ratePLNvsEUR);
     	   
     	   return String.format("to %s - price in PLN:	%d", l[1], priceInPln);
        })
        .collect(Collectors.toList());

    for (String r : result) System.out.println(r);
  }
}
