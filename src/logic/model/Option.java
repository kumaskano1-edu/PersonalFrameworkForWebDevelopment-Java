package model;

import java.io.Serializable;

public class Option implements Serializable   {
private String OptionName;
private double basePrice;
//Constructors
public Option(String name, double basePrice) {
   this.OptionName = name;
   this.basePrice = basePrice;
}
public Option() {
   this.OptionName = "";
}
public Option(String givenWord) {
   this.OptionName = FilterString(givenWord, " ");
   if(givenWord.contains("$")) {
      String word = FilterString(givenWord, "[^0-9]");
      if (givenWord.contains("-")) {
         this.basePrice = -1 * Double.parseDouble(word);
      } else {
         this.basePrice = Double.parseDouble(word);
      }
   }
}

//Geters
protected String getName() {
   return OptionName;
}
protected double getBasePrice() {
   return basePrice;
}
//Seters

protected void setName(String name) {
   this.OptionName = name;
}
protected void setBasePrice(double basePrice) {
   this.basePrice = basePrice;
}
//Methods
protected String FilterString(String word, String condition ) {
   word = word.replaceAll(condition, " ");
   return word.trim();
}
protected void print() {
   System.out.printf("~ %s: $%.2f\n", OptionName, basePrice);
}
@Override
public String toString() {
   StringBuilder str = new StringBuilder(this.OptionName);
   if(basePrice > 0.0 ||  basePrice < 0.0) {
      str.append("[price: " + basePrice + "$" + "], " );
   } else {
      str.append(", ");
   }
   return String.valueOf(str);
}
}
