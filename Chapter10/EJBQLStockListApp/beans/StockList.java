package beans;

import javax.ejb.Remote;

@Remote
public interface StockList {
  // the public business methods on the Stock List bean
  public String[] getSizeStocks(int siz);
  public String[] getAllStocks();
  public String getStock(String ticker);
}