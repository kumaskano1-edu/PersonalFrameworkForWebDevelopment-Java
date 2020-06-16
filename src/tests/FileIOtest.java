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
public class FileIOtest {

@Test
public void TestingHashMap() throws AutoException {
   FileIO no = new FileIO();
   Automobile car = no.readPropData("C:\\Users\\Kuma\\IdeaProjects\\Advanced Java 36A\\CIS 35B - lab5 server\\src\\data\\FordWagon.prop");

   System.out.print(car.getChoice().size());
}
}
