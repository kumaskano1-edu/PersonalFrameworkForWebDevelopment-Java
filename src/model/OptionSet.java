package model;
import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable{
private String name;
private ArrayList<Option> optionList;
private Option choice;

//Constructor
public OptionSet(String Name, int size) {
   this.name = Name;
   this.optionList = new ArrayList<Option>(size);
   for(int i = 0; i < optionList.size(); i++) {
      Option newOption = new Option();
      optionList.set(i,newOption);
   }
}
public OptionSet() {
   this.name = name;
   this.optionList = new ArrayList<>();
}
public OptionSet(String Name) {
   this(Name, 0);
}
//Geters
protected String getName() {
   return name;
}
protected ArrayList<Option> getOptionList() {
   return optionList;
}
protected Option getChoice() {
   return choice;
}
protected Option getSingleOption(int index) {
   return optionList.get(index);
}
//Seters
protected void setName(String name) {
   this.name = name;
}
protected void setOptionList(ArrayList<Option> optionList) {
   this.optionList = optionList;
}
protected void setSingleOption(int index,Option newOption) {
   this.optionList.set(index, newOption);
}
protected void setSingleOption(int index, String name, double basePrice) {
   Option newOption = new Option(name, basePrice);
   setSingleOption(index, newOption);
}
protected void setChoice(Option choice) {
   this.choice = choice;
}
protected void setSingleOptionName(int index, String name) {
   this.optionList.get(index).setName(name);
}
protected void setSingleOptionPrice(int index, double price) {
   this.optionList.get(index).setBasePrice(price);
}
//Finder
protected int FindOptionByName(String name) {
   for(int i = 0; i < optionList.size(); i++) {
      if(optionList.get(i).getName().equals(name)) {
         return i;
      }
   }
   return -1;
}
//Adders
protected boolean addOption(String word) {
   Option newOption  = new Option(word);
   optionList.add(newOption);
   return true;
}
protected boolean addOption(String word, double price){
   Option newOption = new Option(word, price);
   optionList.add(newOption);
   return true;
}
//Removers
protected boolean deleteOption(int index) {
   if (index < 0 || index >= optionList.size()) {
      return false;
   }
   optionList.remove(index);
   return true;
}
//Methods
@Override
public String toString() {
   StringBuilder str = new StringBuilder(name + ": ");
   for(Option option: optionList ) {
      str.append(option);
   }
   return String.valueOf(str);
}
}
