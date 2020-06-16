package model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class LinkHashMapAutos {
   private LinkedHashMap<String, Automobile> automobiles;
   /*------CONSTRUCTORS------*/
   public LinkHashMapAutos() {
      this.automobiles = new LinkedHashMap<String, Automobile>();
   }
   /*------GETERS------*/
   //function for getting automobiles
   public LinkedHashMap<String, Automobile> getAutomobiles() {
      return automobiles;
   }
   //function for getting a single automobile from automobiles
   public Automobile getSingleAutomobile(String key) {
      return automobiles.get(key);
   }
   /*------SETERS------*/
   //function for setting automobiles
   public void setAutomobiles(LinkedHashMap<String, Automobile> automobiles) {
      this.automobiles = automobiles;
   }
   //function for setting the single automobile class
   public void setSingleAutomobile(String key, Automobile newAuto) {
      Automobile foundAuto = automobiles.get(key);
      if (foundAuto != null) {
         automobiles.put(key, newAuto);
      }
   }
   //function for changing the make of the single automobile
   public boolean changeNameOfSingleAuto(String key, String make, String model) {
      Automobile auto = automobiles.get(key);
      if(auto == null ) {
         return false;
      }
      auto.setName(model);
      auto.setMake(make);
      String newKey = createKey(auto);
         automobiles.put(newKey,automobiles.remove(key));
      return true;
   }
   //functin for changing the price of the single automobile
   public boolean changePriceOfSingleAuto(String key, double basePrice) {
         Automobile auto = automobiles.get(key);
         if(auto == null)
            return false;
         auto.setBasePrice(basePrice);
         setSingleAutomobile(key, auto);
         return true;
      }
//function  for changing the name of the OptionSet
public boolean changeOptionSetNameOfSinlgeAuto(String key, String optionSetName, String newOptionSetName) {
      Automobile auto = automobiles.get(key);
      if(auto == null)
         return false;
      auto.UpdateOptionSetName(optionSetName, newOptionSetName);
      return true;
   }
// for changing the name of the option
public boolean changeOptionNameOfSingleAuto(String key, String optionSetName, String optionName, String newName) {
   Automobile auto = automobiles.get(key);
   if (auto == null)
      return false;
   int OptionSetIndex = auto.FindOptionSetByName(optionSetName);
   int OptionIndex = auto.FindOptionByName(OptionSetIndex, optionName);
   auto.UpdateOptionName(OptionSetIndex, OptionIndex, newName);
   setSingleAutomobile(key, auto);
   return true;
}
//function for changing the single price of the automobile
public boolean changeOptionPriceOfSingleAuto(String key, String optionSetName, String optionName, double newPrice) {
   Automobile auto = automobiles.get(key);
   if (auto == null)
      return false;
   int OptionSetIndex = auto.FindOptionSetByName(optionSetName);
   int OptionIndex = auto.FindOptionByName(OptionSetIndex, optionName);
   auto.UpdateOptionPrice(OptionSetIndex, OptionIndex, newPrice);
   setSingleAutomobile(key, auto);
   return true;
}
   /*------ADDERS------*/
   //function for adding auto to automobiles
   public boolean addAuto(Automobile newAuto) {
      String key = createKey(newAuto);
      if(automobiles.putIfAbsent(key, newAuto) != null)
         return false;
      return true;
   }
   /*------REMOVERS------*/
   //function for deleting auto from the automobiles
   public boolean deleteElem(String key) {
      if(automobiles.isEmpty())
         return false;
      else if(automobiles.remove(key) == null)
         return false;
      return true;
   }
   //function for creating the key
   public static String createKey(Automobile car) {
      StringBuilder key = new StringBuilder(car.getMake());
      key.append(" ").append(car.getName());
      return key.toString();
   }
   public static String createKey(String make, String model) {
   StringBuilder key = new StringBuilder(make);
   key.append(" ").append(model);
   return key.toString();
}
   //function for printing the link hash maps
   public void printKeys() {
      Set<String> allKeys = automobiles.keySet();
      System.out.print(allKeys);
   }
   public String getKeys() {
      Set<String> allKeys = automobiles.keySet();
      StringBuilder string = new StringBuilder();
      Iterator<String> it = allKeys.iterator();
      while (it.hasNext()) {
         string.append(it.next() + "\n");
      }
      return string.toString();
   }
   public void printWhole() {
      System.out.print(automobiles);
   }
}
