package tests;
import adapter.BuildAuto;
import exception.AutoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import scale.EditOptions;

import java.io.IOException;

public class EditThreadTest {

   @Test
  public void testingOverall() throws IOException, AutoException {
      System.out.println("-------------------------------------------------\n");
      System.out.print("2018 Ford Focus Wagon ZTW object\n");
      System.out.print("--------------------------------------------------\n");
      BuildAuto myCar=new BuildAuto();
      myCar.BuildAuto("C:\\Users\\Kuma\\IdeaProjects\\Advanced Java 36A\\CIS 35B - lab4\\src\\data\\FordWagonZTW.txt");
      myCar.PrintAuto("Ford Wagon ZTW");
      System.out.println();
// Create two EditOption Threads to update the color "Infra-Red Clearcoat" to different names
       System.out.println("Update Color option: Infra-Red Clearcoat  to Red");
      EditOptions eo1 = new EditOptions(1,"Ford", "Wagon ZTW", "Colors", "Infra-Red Clearcoat", "Red");
       System.out.println("Update Color option: Infra-Red Clearcoat  to Cherry Apple");
       EditOptions eo2 = new EditOptions(2,"Ford", "Wagon ZTW", "Colors", "Infra-Red Clearcoat", "Cherry Apple");
      eo1.start();
      eo2.start();

      myCar.PrintAuto("Ford Wagon ZTW");

   }
}
