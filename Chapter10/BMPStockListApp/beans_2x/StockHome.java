package beans_2x;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

public interface StockHome extends EJBHome {
  // the create method for the Stock bean
  public Stock create(String ticker, String name)
    throws CreateException, RemoteException;
  
  // the find by primary key method for the Stock bean
  public Stock findByPrimaryKey(String ticker)
    throws FinderException, RemoteException;
}