package client;

import timer.TimeIt;
import javax.naming.InitialContext;

public class TimeItTester { 
  public static void main(String[] args) throws Exception {
    // Get a naming context
    InitialContext ctx = new InitialContext();

    // Create a TimeIt object
    TimeIt timeIt =
      (TimeIt) ctx.lookup(TimeIt.class.getName());
      
    timeIt.startTimer();
  }
}