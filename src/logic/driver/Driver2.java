package driver;

import client.DefaultSocketClient;

public class Driver2 {
   public static void main(String[] args){
      DefaultSocketClient Client = new DefaultSocketClient("localhost" ,50);
      Client.start();
   }
}
