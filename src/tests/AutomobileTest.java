package tests;

import exception.AutoException;
import model.Automobile;
import model.OptionSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.FileIO;

import java.io.IOException;

public class AutomobileTest {
public Automobile a1 = new Automobile("Ford", "Ford Wagon ZTW", 122.00);

@Before
public void ArrayManipulations() {
   a1.AddOptionSet("Colors");
   a1.AddOptionSet("Motos");
   int index = a1.FindOptionSetByName("Motos");
   a1.AddOption("Motos", "Standard$444");
   a1.UpdateOptionSetName("Colors", "Color Schemes");
   System.out.print(index);
   System.out.print(a1.FindOptionByName(index, "Standard"));
   Assert.assertEquals(true, a1.UpdateOptionName(index, a1.FindOptionByName(index, "Standard"), "Premium"));
 }

@Test
public void checkingOptionSet() {
   a1.AddOptionSet("Testing", 3);
   a1.AddOption("Testing", "Option");
   int index = a1.FindOptionByName(a1.FindOptionSetByName("Testing"), "Option");
   Assert.assertEquals(index, 0);
   a1.print();
 }
}
