package beans_2x;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

public interface Stock extends EJBObject  {
  // the public business methods on the Stock bean
  // these include the accessor methods from the bean
  
  // get the ticker. do not allow ticker to be set through the
  // interface because it is the primary key.
  public String getTickerSymbol() throws RemoteException;
  
  // get and set the name
  public String getName() throws RemoteException;
  public void setName(String name) throws RemoteException;
}