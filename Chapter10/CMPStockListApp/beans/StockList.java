package beans;

import javax.ejb.Remote;

@Remote
public interface StockList {
  // the public business methods on the Stock List bean
  public String getStock(String ticker)
    throws StockException;
  public void addStock(String ticker, String name)
    throws StockException;
  public void updateStock(String ticker, String name)
    throws StockException;
  public void deleteStock(String ticker)
    throws StockException;
}