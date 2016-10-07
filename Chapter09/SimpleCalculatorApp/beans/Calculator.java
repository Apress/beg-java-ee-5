package beans;

import javax.ejb.Remote;

@Remote
public interface Calculator {
  // The public business methods on the Calculator bean
  public void clearIt();
  public void calculate(String operation, int value);
  public int getValue();
}
