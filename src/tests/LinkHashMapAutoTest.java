package tests;
import adapter.BuildAuto;
import adapter.ProxyAutomotive;
import exception.AutoException;
import model.Automobile;
import model.LinkHashMapAutos;
import model.OptionSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.FileIO;

import java.io.File;
import java.io.IOException;

public class LinkHashMapAutoTest {
LinkHashMapAutos automobiles;
Automobile a1;
Automobile a2;

@Before
public void Initialize() throws IOException, AutoException {
   String URL = "C:\\Users\\Kuma\\IdeaProjects\\Advanced Java 36A\\CIS 35B - lab3\\src\\data\\FordWagonZTW.txt";
   String URL2 = "C:\\Users\\Kuma\\IdeaProjects\\Advanced Java 36A\\CIS 35B - lab3\\src\\data\\BmwModelX.txt";
   automobiles = new LinkHashMapAutos();
   a1 = FileIO.buildAuto(URL);
   a2 = FileIO.buildAuto(URL2);
}

@Test
public void TestingHashMap() {
   automobiles.addAuto(a1);
   Automobile selected = automobiles.getSingleAutomobile("Ford Wagon ZTW");
   automobiles.changeNameOfSingleAuto("Ford Wagon ZTW", "Ford", "Wagon 3");
   //automobiles.printWhole();
   selected.print();
   System.out.print(selected.getChoice());
}

}