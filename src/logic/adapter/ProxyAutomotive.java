package adapter;

import exception.AutoException;
import model.Automobile;
import model.LinkHashMapAutos;
import util.FileIO;

import java.io.IOException;
import java.util.*;

public abstract class ProxyAutomotive {
private static LinkHashMapAutos automobiles;

static {
   automobiles = new LinkHashMapAutos();
}

//change specific auto price, name
//add optionSet for specific auto
//add option for specific optionSet
//remove specific auto from the list
public LinkHashMapAutos getAutos() {
   return this.automobiles;
}
public void removeAuto(String key) {
   automobiles.deleteElem(key);
}

public void BuildAuto(String fileName) throws IOException, AutoException {
   Automobile carBuilt = FileIO.buildAuto(fileName);
   automobiles.addAuto(carBuilt);
}
public void BuildAuto(Automobile car) {
   automobiles.addAuto(car);
}
public void BuildAuto(Properties props) throws AutoException {
   FileIO f1 = new FileIO();
   Automobile a1 = f1.readPropData(props);
   automobiles.addAuto(a1);
}

public void ManuallyBuildAuto() {
   Scanner input = new Scanner(System.in);
   System.out.print("Input the car name: ");
   String carName = input.nextLine();
   System.out.print("Input the Price for the car: ");
   Double carPrice = null;
   try {
      carPrice = input.nextDouble();
   } catch (InputMismatchException e) {
      carPrice = 00.0;
   }
   Automobile newCar = new Automobile(carName, carPrice);
   automobiles.addAuto(newCar);
}

public void fix(int error) throws IOException, AutoException {
   AutoException ae = new AutoException(error);
   ae.fix(error);
}

public static Automobile getAuto(String model) {
   return automobiles.getSingleAutomobile(model);
}

public void printListOfKeys() {
   automobiles.printKeys();
}
public String getAllKeys() {
  return automobiles.getKeys();
}
public void PrintWholeList() {
   automobiles.printWhole();
}

public void PrintAuto(String modelName) {
   Automobile carFound = automobiles.getSingleAutomobile(modelName);
   if (carFound == null) {
      System.out.print("No Car with such name exists!");
   } else {
      carFound.print();
   }
}

public void UpdateOptionSetName(String ModelName, String OptionSetName, String newName) {
   boolean success = automobiles.changeOptionSetNameOfSinlgeAuto(ModelName, OptionSetName, newName);
   if (!success) {
      System.out.print("Unforntunately we were not able to perform this operation");
   }
}

public void updateOptionName(String ModelName, String OptionSetName, String
        Option, String newName) throws AutoException {
   boolean sucess = automobiles.changeOptionNameOfSingleAuto(ModelName, OptionSetName, Option, newName);
   if (!sucess) {
      System.out.print("Unforntunately we were not able to perform this operation");
   }
}

public void updateOptionPrice(String ModelName, String OptionSetName, String
        Option, double newprice) {
   boolean sucess = automobiles.changeOptionPriceOfSingleAuto(ModelName, OptionSetName, Option, newprice);
   if (!sucess) {
      System.out.print("Unforntunately we were not able to perform this operation");
   }
}
public void chooseOptions(Automobile a1) {

   Scanner sc1 = new Scanner(System.in);
   for (int i = 0; i < a1.getOptionSet().size(); i++) {
      boolean done = false;
      while (done == false) {
         String setName = a1.getSingleOptionSetName(i);
         System.out.println("Please enter number for choice of " + setName);
         int num = 1;
         int answer;
         // iterate over options and print option name & price
         for (int j = 0; j < a1.getOps(i).size(); j++) {
            num = j + 1;
            System.out.printf(num + ". " + a1.getSingleOptionName(i, j) + ": $%.2f\n", a1.getSingleOptionPrice(i, j));
         }
         answer = sc1.nextInt();
         if (answer >= 1 && answer <= a1.getOps(i).size()) {
            String optionName = a1.getSingleOptionName(i, answer - 1);
            a1.setOptionChoice(i, answer - 1);
            done = true;
         } else {
            System.out.println("Error: Choice number not in range.\n");
         }
      }
   }
   System.out.println();
   try {
      automobiles.addAuto(a1);
   } catch (Exception e) {
      e.printStackTrace();
   }
}
public String listAutos() {
   StringBuilder response = new StringBuilder();
   List<String> list = new ArrayList<>();
   Set<String> keys = this.automobiles.getAutomobiles().keySet();
   for(String k: keys) {
      list.add(k);
   }
   Collections.sort(list);
   for (String str : list) {
      response.append(str+"\n");
   }
   return response.toString();
}
}
