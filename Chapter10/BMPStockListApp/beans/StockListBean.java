package beans;

import beans_2x.Stock;
import beans_2x.StockHome;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

@Stateless
public class StockListBean implements StockList {
  
  // the public business methods. these must be coded in the 
  // remote interface also. 
  
  public String getStock(String ticker)
    throws FinderException {
    try {
      StockHome stockHome = getStockHome();
      Stock stock = stockHome.findByPrimaryKey(ticker);
      return stock.getName();
    }
    catch (FinderException fe) {
      throw fe;
    }
    catch (Exception ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }
  
  public void addStock(String ticker, String name)
    throws CreateException {
    try {
      StockHome stockHome = getStockHome();
      stockHome.create(ticker, name);
    }
    catch (CreateException ce) {
      throw ce;
    }
    catch (Exception ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }
    
  public void updateStock(String ticker, String name)
   throws FinderException {
    try {
      StockHome stockHome = getStockHome();
      Stock stock = stockHome.findByPrimaryKey(ticker);
      stock.setName(name);
    }
    catch (FinderException fe) {
      throw fe;
    }
    catch (Exception ex) {
      throw new RuntimeException(ex.getMessage());
    }
   }
   
  public void deleteStock(String ticker)
    throws FinderException {
    try {
      StockHome stockHome = getStockHome();
      Stock stock = stockHome.findByPrimaryKey(ticker);
      stock.remove();
    }
    catch (FinderException fe) {
      throw fe;
    }
    catch (Exception ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }
  
  private StockHome getStockHome() throws NamingException {
    // get the initial context
    InitialContext initial = new InitialContext();
    
    // get the object reference
    Object objref = initial.lookup("beans_2x.Stock");
    StockHome home = (StockHome)
      PortableRemoteObject.narrow(objref, StockHome.class);
    return home;
  }
  
  // standard ejb methods
  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void ejbRemove() {}
  public void ejbCreate() {}
  public void setSessionContext(SessionContext context) { }
}