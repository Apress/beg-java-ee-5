package beans;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.Remote;

@Remote
public interface StockList {
  // the public business methods on the Stock List bean
  public String getStock(String ticker)
    throws FinderException;
  public void addStock(String ticker, String name)
    throws CreateException;
  public void updateStock(String ticker, String name)
    throws FinderException;
  public void deleteStock(String ticker)
    throws FinderException;
}