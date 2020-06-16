package scale;

import adapter.ProxyAutomotive;
import exception.AutoException;
import model.Automobile;
import model.LinkHashMapAutos;
import model.Option;
import model.OptionSet;

import java.io.IOException;

public class EditOptions extends ProxyAutomotive implements Runnable, EditThread { //by extending Pro
   private String make;
   private String model;
   private String optionSetName;
   private String newOptionSetName;
   private String optionName;
   private String newOptionName;
   private double newOptionPrice;
   /*Thread Properties*/
   private Thread thread;
   public int threadNum;
   private static int CountForThread = 1; //inital thread no
   private String operation; //checking what operation will be performed
   private boolean processing;

   /*Methods*/

   public EditOptions(int threadNum, String make, String model, String optionSetName, String newOptionSetName) {
      thread = new Thread(this);
      this.make = make;
      this.model = model;
      this.optionSetName = optionSetName;
      this.newOptionSetName = newOptionSetName;
      this.threadNum=threadNum;
      this.processing =false;
      this.operation ="OptionSetName";
      CountForThread++;
   }
   public EditOptions(int threadNum, String make, String model, String optionSetName,String optionName, String newOptionName) throws AutoException {
      thread = new Thread(this);
      this.make = make;
      this.model = model;
      this.optionSetName = optionSetName;
      this.optionName=optionName;
      this.newOptionName = newOptionName;
      this.threadNum=threadNum;
      this.processing=false;
      this.operation="OptionName";
      CountForThread++;
   }
   public EditOptions(int threadNum, String make, String model, String optionSetName,String optionName, double newOptionPrice) {
      thread = new Thread(this);
      this.make = make;
      this.model = model;
      this.optionSetName = optionSetName;
      this.optionName=optionName;
      this.newOptionPrice = newOptionPrice;
      this.threadNum=threadNum;
      this.processing=false;
      this.operation="OptionPrice";
      CountForThread++;
   }

   /*INTERFACES METHODS OVERIDDING*/
   public void updateOptionSetName(int threadNum,String make, String model, String optionSetName, String newOptionSetName)
           throws AutoException {
      String key= LinkHashMapAutos.createKey(make, model);
      Automobile auto = ProxyAutomotive.getAuto(key);

      if (auto != null){
         synchronized (auto) {
            while (processing) {
               try {
                  System.out.println("Thread " + Integer.toString(threadNum) + " waiting.");
                  auto.wait();
               }
               catch (InterruptedException ie){
                  System.out.println("Thread " + Integer.toString(threadNum) + "interrupted.");
               }
            }
            processing=true;
            if(!auto.UpdateOptionSetName(optionSetName, newOptionSetName)) {
               System.out.println("Thread " + Integer.toString(threadNum) + " Could Not Update The Option");

            } else {
               System.out.println("Thread " + Integer.toString(threadNum) + " updating Option Name");
               processing = false;
               System.out.println("Thread " + Integer.toString(threadNum) + " finished updating Option Name");
            }
            auto.notifyAll();
            System.out.println("All threads notified.");
         }
      } else {
         System.out.print("Unfortunately there is not thread with this name");
      }
   }
   public void updateOptionName(int threadNum, String make, String model, String optionSetName, String optionName, String newOptionName)
           throws AutoException {
      String key= LinkHashMapAutos.createKey(make, model);
      Automobile auto = ProxyAutomotive.getAuto(key);

      if (auto != null){
         synchronized (auto) {
            while (processing) {
               try {
                  System.out.println("Thread " + Integer.toString(threadNum) + " waiting.");
                  auto.wait();
               }
               catch (InterruptedException ie){
                  System.out.println("Thread " + Integer.toString(threadNum) + " interrupted.");
               }
            }
            processing=true;
            int OptionSetName = auto.FindOptionSetByName(optionSetName);
            int OptionName = auto.FindOptionByName(OptionSetName, optionName);
            if(!auto.UpdateOptionName(OptionSetName, OptionName, newOptionName)) {
               System.out.println("Thread " + Integer.toString(threadNum) + " Could Not Update The Option");

            } else {
               System.out.println("Thread " + Integer.toString(threadNum) + " updating Option Name");
               processing = false;
               System.out.println("Thread " + Integer.toString(threadNum) + " finished updating Option Name");
            }
            auto.notifyAll();
            System.out.println("All threads notified.");
         }
      } else {
         System.out.print("Unfortunately there is not thread with this name");
      }
   }
public void updateOptionPrice(int threadNum, String make, String model, String optionSetName, String optionName, double newPrice) throws AutoException {
   String key= LinkHashMapAutos.createKey(make, model);
   Automobile auto = ProxyAutomotive.getAuto(key);
   if (auto != null){
      synchronized (auto) {
         while (processing) {
            try {
               System.out.println("Thread " + Integer.toString(threadNum) + " waiting.");
               auto.wait();
            }
            catch (InterruptedException ie){
               System.out.println("Thread " + Integer.toString(threadNum) + " interrupted.");
            }
         }
         processing=true;
         int OptionSetName = auto.FindOptionSetByName(optionSetName);
         int OptionName = auto.FindOptionByName(OptionSetName, optionName);
         if(!auto.UpdateOptionPrice(OptionSetName, OptionName, newPrice)) {
            System.out.println("Thread " + Integer.toString(threadNum) + " Could Not Update The Option");

         } else {
            System.out.println("Thread " + Integer.toString(threadNum) + " updating Option Name");
            processing = false;
            System.out.println("Thread " + Integer.toString(threadNum) + " finished updating Option Name");
         }
         auto.notifyAll();
         System.out.println("All threads notified.");
      }
   } else {
      System.out.print("Unfortunately there is not thread with this name");
   }
}

public void run() {
      System.out.println("Starting Thread " + Integer.toString(threadNum));
      try {
         if (operation.equals("OptionSetName")) {
            updateOptionSetName(threadNum, make,model,optionSetName,newOptionSetName);
         }
         else if (operation.equals("OptionName")) {
            updateOptionName(threadNum,make,model,optionSetName,optionName,newOptionName);
         }
         else {
            throw new AutoException(2);
         }
      }
      catch (AutoException e) {
         System.out.print(e);
      }
      System.out.println("Ending Thread " + Integer.toString(threadNum));
      thread.interrupt();
      return;
   }
   public void start() {
      thread.start();
   }
}
