package model;
import java.io.Serializable;
import java.util.ArrayList;

public class Automobile implements Serializable {
private static final long serialVersionUID = 6529685098267757690L;

private String make;
private String name;
private double basePrice;
private ArrayList<OptionSet> optionSet;
private ArrayList<Option> choice;

/*CHOICE*/

public synchronized ArrayList<Option> getChoice() {
   return choice;
}
public synchronized String getOptionChoice(String setName) {
   int index = FindOptionSetByName(setName);
   if(index < 0) {
      return null;
   }
   return choice.get(index).getName();
}
public synchronized double getOptionChoicePrice(String setName) {
   int index = FindOptionSetByName(setName);
   if(index < 0) {
      return -1;
   }
   return choice.get(index).getBasePrice();
}
public synchronized void addOptionChoice(Option option) {
   choice.add(option);
}
public synchronized void addOptionChoice() {
   choice.add(new Option("Empty"));
}
public synchronized void setOptionChoice(String setName, String optionName) {
   int index = FindOptionSetByName(setName);
   if(index < 0) {
      System.out.print("Unforntunately this Option Set name does not exist \n");
   } else {
      Option optionInside = getSingleOption(index, FindOptionByName(index, optionName)); //getting an option from optionSet by name
      if (optionInside == null) {
         System.out.print("There is no option with the name " + optionName);
      } else {
         choice.set(index, optionInside);
      }
   }
}
public synchronized void setOptionChoice(int index, int index2) {
   Option optionInside = getSingleOption(index, index2);
   choice.set(index, optionInside);
}
/*--------CONSTRUCTORS---------*/
public Automobile() {
   make = "";
   name = "";
   basePrice = 00.00;
   this.optionSet = new ArrayList<OptionSet>(0);
   this.choice = new ArrayList<Option>(0);
}
public Automobile(String name, double basePrice) {
   this.make = "";
   this.name = name;
   this.basePrice = basePrice;
   this.optionSet = new ArrayList<OptionSet>(0);
   this.choice = new ArrayList<Option>(0);

}
public Automobile(String make, String name, double basePrice) {
   this.make = make;
   this.name = name;
   this.basePrice = basePrice;
   this.optionSet = new ArrayList<OptionSet>(0);
   this.choice = new ArrayList<Option>(0);


}
public Automobile(String name, int optionSize) {
   this.name = name;
   this.optionSet = new ArrayList<OptionSet>(optionSize);
   this.choice = new ArrayList<Option>(optionSize);

}

/*--------GETERS---------*/
public synchronized String getMake(){return  make;}
public synchronized String getName() {
   return name;
}
public synchronized double getBasePrice() {
   return basePrice;
}
public synchronized ArrayList<OptionSet> getOptionSet() {
   return optionSet;
}
public synchronized OptionSet getSingleOptionSet(int index) {
   if(index < 0 || index >= optionSet.size() || optionSet.isEmpty()) {
      return null;
   }
   return optionSet.get(index);
}

public synchronized String getSingleOptionSetName(int index) {
   if(index < 0 || index >= optionSet.size() || optionSet.isEmpty()) {
      return null;
   }
   return optionSet.get(index).getName();
}
public  ArrayList<Option> getOps(int i) {
   return optionSet.get(i).getOptionList();
}
/*OPTION GETTERS*/
public synchronized Option getSingleOption(int optsSetIndexm, int index) {
   if(optsSetIndexm < 0 || optsSetIndexm >= optionSet.size() || index < 0 || index >= optionSet.get(optsSetIndexm).getOptionList().size()) {
      return null;
   }
   return optionSet.get(optsSetIndexm).getSingleOption(index);
}
public synchronized String getSingleOptionName(int OptSetIndex, int index) {
   if(OptSetIndex < 0 || OptSetIndex >= optionSet.size() || index < 0 || index >= optionSet.get(OptSetIndex).getOptionList().size()) {
      return null;
   }
   return optionSet.get(OptSetIndex).getSingleOption(index).getName();
}
public synchronized double getSingleOptionPrice(int OptSetIndex, int index) {
   if(OptSetIndex < 0 || OptSetIndex >= optionSet.size() || index < 0 || index >= optionSet.get(OptSetIndex).getOptionList().size()) {
      return Double.NaN;
   }
   return optionSet.get(OptSetIndex).getSingleOption(index).getBasePrice();
}
/*--------SETERS---------*/
public synchronized void setMake(String make) {this.make = make;}
public synchronized void setName(String name) {
   this.name = name;
}
public synchronized void setBasePrice(double basePrice) {
   this.basePrice = basePrice;
}
public synchronized void setOptionSet(ArrayList<OptionSet> optionSet) {
   this.optionSet = optionSet;
}
public synchronized void setSingleOption(int index, OptionSet opt) {
   this.optionSet.set(index, opt);
}
public synchronized void SetSingelOptionSetName(int index, String name) {
   this.optionSet.get(index).setName(name);
}
public synchronized void SetSingleOption(int OptionSetIndex, int index, Option option) {
   this.optionSet.get(OptionSetIndex).setSingleOption(index, option);
}
public synchronized void SetSingleOptionName(int OptionSetIndex, int index, String name) {
   this.optionSet.get(OptionSetIndex).setSingleOptionName(index, name);
}
public synchronized void SetSingleOptionPrice(int OptionSetIndex, int index, double basePrice) {
   this.optionSet.get(OptionSetIndex).setSingleOptionPrice(index, basePrice);
}
/*----Finders----*/
public synchronized int FindOptionSetByName(String name) {
   if(optionSet.isEmpty()) {
      return -1;
   }
   for(int i = 0; i < optionSet.size(); i++) {
      if(optionSet.get(i).getName().equals(name)) {
         return i;
      }
   }
   return -1;
}
public synchronized int FindOptionByName(int optIndex, String optionName) {
   if (optIndex == -1) {
      return -1;
   }
   int index = optionSet.get(optIndex).FindOptionByName(optionName);
   return index;
}
/*----Updaters----*/
public synchronized boolean UpdateOptionSet(String optionSetName, OptionSet newOpts) {
   int index  = FindOptionSetByName(optionSetName);
   if(index == -1) {
      return false;
   }
   optionSet.set(index, newOpts);
   return true;
}
public synchronized boolean UpdateOptionSetName(String optionSetName, String newName) {
   int index  = FindOptionSetByName(optionSetName);
   if(index == -1) {
      return false;
   }
   optionSet.get(index).setName(newName);
   return true;
}
public synchronized boolean UpdateOption(int optSIndex, int OptionIn, Option newOption) {
   if (optSIndex < 0 || optSIndex >= optionSet.size() ||
           OptionIn < 0 || OptionIn >= optionSet.get(optSIndex).getOptionList().size())
      return false;
   optionSet.get(optSIndex).setSingleOption(OptionIn, newOption);
   return true;
}
public synchronized boolean UpdateOptionName(int optSIndex, int OptionIn, String newName) {
   if (optSIndex < 0 || optSIndex >= optionSet.size() ||
           OptionIn < 0 || OptionIn >= optionSet.get(optSIndex).getOptionList().size())
      return false;
   optionSet.get(optSIndex).setSingleOptionName(OptionIn, newName);
   return true;
}
public synchronized boolean UpdateOptionPrice(int optSIndex, int OptionIn, double basePrice) {
   if (optSIndex < 0 || optSIndex >= optionSet.size() ||
           OptionIn < 0 || OptionIn >= optionSet.get(optSIndex).getOptionList().size())
      return false;
   optionSet.get(optSIndex).setSingleOptionPrice(OptionIn, basePrice);
   return true;
}
/*-----Adders-----*/
public synchronized void AddOptionSet(String name) {
   OptionSet newOptionSet = new OptionSet(name);
   optionSet.add(newOptionSet);
}
public synchronized void AddOptionSet(String name, int size) {
   OptionSet newOptionSet = new OptionSet(name, size);
   optionSet.add(newOptionSet);
}
public synchronized boolean AddOption(String OptionSetName, String word) {
   int OptionSetIndex = FindOptionSetByName(OptionSetName);
   if(OptionSetIndex == -1) {
      return false;
   }
   optionSet.get(OptionSetIndex).addOption(word);
   return true;
}
public synchronized void addOptionValue(int i, String name, float price) {
   this.optionSet.get(i).addOption(name, price);
}
public synchronized void printChoices() {
   System.out.printf("Your Option Choices for %s:\n", name);
   for (int i = 0; i < choice.size(); i++) {
      choice.get(i).print();
   }
   getTotalPrice();
   System.out.printf("Total cost - $%.2f", getTotalPrice());
}
public synchronized void clearChoice() {
   this.choice.clear();
}
public synchronized  boolean AddOption(String optionSetName, String word, double price) {
   int OptionSetIndex = FindOptionSetByName(optionSetName);
   if(OptionSetIndex == -1) {
      return false;
   }
   optionSet.get(OptionSetIndex).addOption(word, price);
   return true;   }
/*----Methods----*/
public synchronized void print() {
   System.out.print(toString());
}
public synchronized int getTotalPrice() {
   double price = basePrice;
   for(Option elem: choice) {
      price += elem.getBasePrice();
   }
   return (int) price;
}
@Override
public synchronized String toString() {
   StringBuilder str = new StringBuilder();
   str.append("make " + make + "\n" + "name: " + name + "\n" + "price: " + basePrice + "$ " + "\n");
   optionSet.forEach((elem) -> str.append(elem + "\n"));
   str.append("\n");
   return String.valueOf(str);
}
}
