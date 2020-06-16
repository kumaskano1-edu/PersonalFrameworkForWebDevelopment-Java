package tests;
import adapter.BuildAuto;
import adapter.ProxyAutomotive;
import exception.AutoException;
import model.Automobile;
import model.OptionSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.FileIO;

import java.io.File;
import java.io.IOException;

public class ProxyAutomobileTest {
   public BuildAuto a1;
   public Automobile a2;

   @Test
   public void testingProxyAuto() throws IOException, AutoException {
         String URL = "C:\\Users\\Kuma\\IdeaProjects\\Advanced Java 36A\\CIS 35B - lab3\\src\\data\\FordWagonZTW.txt";
         a1 = new BuildAuto();
         a1.BuildAuto(URL);
         a2 = FileIO.buildAuto(URL);
         a1.printListOfKeys();
         a1.updateOptionPrice("Ford Wagon ZTW", "Colors", "Fort Know Gold Vampire", 222);
         a1.UpdateOptionSetName("Ford Wagon ZTW", "Colors", "Color");
         a1.updateOptionPrice("Ford Wagon ZTW", "Color", "Fort Know Gold Vampire", 0);
         a2.setOptionChoice("Transmission", "Manual");
         a2.setOptionChoice("Side-Impact-Air-Bags", "present");
         a2.print();
         //System.out.print(a2.calculateThePrice());

      //Assert.assertEquals("Ford Wagon ZTW",a1.getAuto().getName());
   }
}
