package util;

import exception.AutoException;
import model.Automobile;
import model.OptionSet;

import java.io.*;
import java.util.Properties;

public class FileIO {
public static void WriteObject(Automobile car, String URL) throws FileNotFoundException {
   try {
      FileOutputStream fileOut = new FileOutputStream(URL);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(car);
      out.close();
      fileOut.close();
      System.out.print("\n File has been sucessfuly serialized in SerializedObject.ser \n");
   } catch (IOException e) {
      e.printStackTrace();
   }
}
public static void WriteLog(String fileName, String message) throws IOException {
   try{
      File file =new File(fileName);
      if(!file.exists()){
         file.createNewFile();
      }
      FileWriter fw = new FileWriter(file,true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      //This will add a new line to the file content
      /* Below three statements would add three
       * mentioned Strings to the file in new lines.
       */
      pw.println(message);
      pw.close();
   }catch(IOException ioe){
      System.out.println("Exception occurred:");
      ioe.printStackTrace();
   }
}
public static Automobile readObject(String URL) {
   Automobile car = null;
   try {
      FileInputStream fileIn = new FileInputStream(URL);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      car = (Automobile) in.readObject();
      in.close();
      fileIn.close();
   } catch (IOException i) {
      i.printStackTrace();
   } catch (ClassNotFoundException c) {
      c.printStackTrace();
   }
   return car;
}
public static Automobile buildAuto(String fileName) throws IOException, AutoException {
   try {
      File source = new File(fileName);
      if (!source.exists()) { //check if the file given exists
         throw new AutoException(1);
      } else if(source.length() <= 0) { //checking if it is not empty
         throw new AutoException(5);
      }
      FileReader file = new FileReader(fileName);
      BufferedReader buffer = new BufferedReader(file);
      boolean endOfFile = false;
      String make = buffer.readLine();
      String name = buffer.readLine();
      Double price = 0.0; //using instantiated 0 to avoid null pointer
      try {
         price = Double.parseDouble(buffer.readLine()); //if the string is un parsable then it will throw an error
      } catch (NumberFormatException e) {
         /*Instantiating the exception class instead of using try, catch block to avoid block redundancy*/
         AutoException PriceProblem = new AutoException(3);
         PriceProblem.fix(PriceProblem.getErrorno());
      } finally {
         Automobile newAuto = new Automobile(make,name, price);
         while (!endOfFile) {
            String line = buffer.readLine();
            if (line == null) {
               endOfFile = true;
               break;
            }
            else {
               String OptionSetName = line; // Creating the OptionSet Name
               if(OptionSetName.length() <= 1) { //in the case if the option set is empty
                  throw new AutoException(2);
               }
               newAuto.AddOptionSet(OptionSetName);
               buffer.readLine();
               line = buffer.readLine();// checking for the {
               if (line.length() <= 0) {
                  throw new AutoException(4);
               }
               while (line.charAt(0) != '}') {
                  newAuto.AddOption(OptionSetName, line);
                  line = buffer.readLine();
               }
               newAuto.addOptionChoice(newAuto.getSingleOption(newAuto.FindOptionSetByName(OptionSetName), 0)); //adding default choice
            }
         }
         return newAuto;
      }
   } catch (AutoException e) {
      e.fix(e.getErrorno());
   }
   return null;
}
public Automobile readPropData(String fname) throws AutoException  {
   Automobile a1 = new Automobile();
   Properties props = new Properties();
   int opCount=1;

   // try block to catch exceptions
   try {
      //opens a fileinput stream connection
      FileInputStream in = new FileInputStream(fname);
      props.load(in); //load entire file in memory
   }
   catch (Exception e1){
      System.out.println("Error -- " + e1.toString());
   }
   a1=readPropData(props);
   return a1;
}
public Automobile readPropData(Properties props) {
   Automobile a1 = new Automobile();
   int opCount=1;
   String make=props.getProperty("Make");
   a1.setMake(make);
   String model=props.getProperty("Model");
   a1.setName(model);
   double base=Double.parseDouble(props.getProperty("BasePrice"));
   a1.setBasePrice(base);
   // add options
   String optionString="Option"+String.valueOf(opCount); //ie Option1, Option2

   while (props.getProperty(optionString)!=null) {  //if the optionSet name exists
      String optionName=props.getProperty(optionString); //ie. Color
      a1.getOptionSet().add(new OptionSet(optionName));
      a1.addOptionChoice();
      int letter=65; //int value of letter A
      String opNameStr=optionString+Character.toString((char) letter)+"_name"; //ie. Option1A_name
      String opPriceStr=optionString+Character.toString((char) letter)+"_price"; //ie. Option1A_price
      while (props.getProperty(opNameStr) != null){   //if the option exists, add it
         a1.addOptionValue(opCount-1,props.getProperty(opNameStr),Float.parseFloat(props.getProperty(opPriceStr)));
         letter++;
         opNameStr=optionString+Character.toString((char) letter)+"_name";
         opPriceStr=optionString+Character.toString((char) letter)+"_price";
      }
      opCount++;
      optionString="Option"+String.valueOf(opCount); //set to next option, ie. Option2

   }
   return a1;
}
public Automobile buildAutoObject(String filename) throws AutoException, IOException {
   Automobile a1 = null;

   if (filename.contains(".txt"))
      a1 = FileIO.buildAuto(filename);
   if (filename.contains(".prop"))
      a1 = readPropData(filename);
   System.out.print("Make: " + a1.getMake());
   System.out.print("Model: " + a1.getName());
   System.out.print("Base Price: " + a1.getBasePrice());
   System.out.println("Automobile object sucessfully built. \n");
   FileIO.WriteObject(a1, "C:\\Users\\Kuma\\IdeaProjects\\Advanced Java 36A\\CIS 35B - lab5 server\\src\\data\\Auto.ser");
   return a1;
}

}
