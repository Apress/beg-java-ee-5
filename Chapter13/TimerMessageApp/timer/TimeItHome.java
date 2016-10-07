package timer;

import java.rmi.RemoteException;
import javax.ejb.EJBHome;
import javax.ejb.CreateException;

public interface TimeItHome extends EJBHome {
  // the create method for the timer bean.
  public TimeIt create()
    throws CreateException, RemoteException;

}