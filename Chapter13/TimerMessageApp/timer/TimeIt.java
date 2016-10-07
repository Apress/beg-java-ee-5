package timer;

import javax.ejb.Remote;

@Remote
public interface TimeIt {
  // the public business method on the timer bean
  public void startTimer();
}