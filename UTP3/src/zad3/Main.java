/**
 *
 *  @author Legięć Bartosz S19129
 *
 */

package zad3;


import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {
    ProgLang pl = null;
    try {
      pl =  new ProgLang(System.getProperty("user.home") + "/Programmers.tsv");
    } catch (Exception exc) {
      System.out.println("Wadliwy konstruktor: " + exc);
    }
    System.out.println("@1 Mapa językow:");
    try {
      pl.getLangsMap().forEach((k,v)->System.out.println(k+ " = " + v));
    } catch (Exception exc) {
      System.out.println(exc);
    }
    System.out.println("@2 Mapa programistów:");
    try {
      pl.getProgsMap().forEach((k,v)->System.out.println(k+ " = " + v));
    } catch (Exception exc) {
      System.out.println(exc);
    }
    System.out.println("@3 Języki posortowane wg liczby programistów:");
    try {
      pl.getLangsMapSortedByNumOfProgs()
        .forEach((k,v)->System.out.println(k+ " = " + v));
    } catch (Exception exc) {
      System.out.println(exc);
    }
    System.out.println("@4 Programiści posortowani wg liczby języków:");
    try {
      pl.getProgsMapSortedByNumOfLangs()
        .forEach((k,v)->System.out.println(k+ " = " + v));
    } catch (Exception exc) {
      System.out.println(exc);
    }
    System.out.println("@5 Oryginalna mapa języków niezmieniona:");
    try {
      pl.getLangsMap().forEach((k,v)->System.out.println(k+ " = " + v));
    } catch (Exception exc) {
      System.out.println(exc);
    }
    System.out.println("@6 Oryginalna mapa programistów niezmienione:");
    try {
      pl.getProgsMap().forEach((k,v)->System.out.println(k+ " = " + v));
    } catch (Exception exc) {
      System.out.println(exc);
    }
    System.out.println("@7 Mapa programistów znających więcej niż 1 język:");
    try {
      pl.getProgsMapForNumOfLangsGreaterThan(1)
        .forEach((k,v)->System.out.println(k+ " = " + v));
    } catch (Exception exc) {
      System.out.println(exc);
    }
    System.out.println("@8 Oryginalna mapa programistów nie jest zmieniona:");
    try {
      pl.getProgsMap().forEach((k,v)->System.out.println(k+ " = " + v));
    } catch (Exception exc) {
      System.out.println(exc);
    }
  }

}
