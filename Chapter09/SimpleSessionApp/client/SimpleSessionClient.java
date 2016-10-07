package client;

import beans.SimpleSession;
import javax.naming.InitialContext;

public class SimpleSessionClient {
  public static void main(String[] args) throws Exception
  {
    InitialContext ctx = new InitialContext();
    SimpleSession simpleSession 
      = (SimpleSession) ctx.lookup(SimpleSession.class.getName());
    for (int i = 0; i < args.length; i++) {
      String returnedString = simpleSession.getEchoString(args[i]);
      System.out.println("sent string: " + args[i] +
                         ", received string: " + returnedString);
    }
  }
}