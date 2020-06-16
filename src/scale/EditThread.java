package scale;

import exception.AutoException;

public interface EditThread {
   public abstract void updateOptionSetName(int threadNum,String make, String model, String optionSetName, String newOptionSetName) throws AutoException;
   public abstract void updateOptionName(int threadNum, String make, String model, String optionSetName, String optionName, String newOptionName) throws AutoException;
   public abstract void updateOptionPrice(int threadNum, String make, String model, String optionSetName, String optionName, double newPrice) throws AutoException;
   //public abstract void updateOptionPrice(int threadNumber, String key, String optionSetName, String option, double newPrice) throws AutoException;
}
