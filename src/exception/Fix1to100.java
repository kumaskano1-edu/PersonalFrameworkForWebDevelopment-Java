package exception;

import adapter.BuildAuto;
import adapter.createAuto;
import util.FileIO;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fix1to100 {
   private String errorMessage;

   public void FileNameNotFound(int error) throws IOException, AutoException {
      errorMessage =  handleException(error, "File Name you specified does not exist, switched to the car template \n");
      logError();
      /*Fix by creating an auto object, by using the template txt file*/
      createAuto newAuto = new BuildAuto();
      newAuto.BuildAuto("C:\\Users\\Kuma\\IdeaProjects\\Advanced Java 36A\\CIS 35B - Lab2\\src\\data\\SampleCar.t xt"); //sample car in the case of failure
      newAuto.PrintAuto(null);
   }
   public void OptionSetNameNotFound(int error) throws IOException {
      errorMessage = handleException(error, "OptionSet name was not read!");
      logError();
   }
   public void PriceIsInvalid(int error) throws IOException {
      errorMessage = handleException(error, "Price or any data in line after car name is missing, manual entry is activated");
      logError();
   }
   public void MissingOptionSetOptions(int error) throws IOException {
      errorMessage = handleException(error, "Missing Option Set Options, error withing the text file");
      logError();
   }
   public void EmptyFileUsed(int error) throws IOException {
      errorMessage = handleException(error, "File is empty, has no data in it, manual mode was started: \n");
      logError();
      /*Another fixing implementation, uses the Manually build auto mode from Proxy automotive to fix the error*/
      createAuto newAuto = new BuildAuto();
      newAuto.ManuallyBuildAuto();
      newAuto.PrintAuto(null);
   }
   /*Function handles or generate the error by combining the error num, message and date*/
   public String handleException(int error, String message) {
      StringBuilder errorMessage = new StringBuilder();
      errorMessage.append("Error no: ").append( + error);
      errorMessage.append(" Exception Message: ").append(message);
      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date date = new Date();
      errorMessage.append(" | time: " + dateFormat.format(date) + "\n");
      return errorMessage.toString();
   }
   /*Login of the error method, uses the FileIO logger created by me*/
   public void logError() throws IOException {
      StringBuilder str = new StringBuilder(errorMessage);
      FileIO.WriteLog("C:\\Users\\Kuma\\IdeaProjects\\Advanced Java 36A\\CIS 35B - Lab2\\src\\logs\\log1.txt", str.toString());
      System.out.print(errorMessage);
   }
}
