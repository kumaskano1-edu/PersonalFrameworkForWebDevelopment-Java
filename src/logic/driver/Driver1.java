package driver;

import adapter.BuildAuto;
import exception.AutoException;
import model.Automobile;
import scale.EditOptions;
import server.DefaultServerSocket;
import util.FileIO;

import java.io.IOException;

public class Driver1 {

public static void main(String[] args) throws AutoException {
   DefaultServerSocket Server= new DefaultServerSocket(50);
   Server.start();
}
}
