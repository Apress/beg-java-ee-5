package beans;

import javax.ejb.Remote;

// general imports
import java.util.*;

@Remote
public interface StockList {
  // the public business methods on the Stock List bean
  public List getStockRatings();
  public List getAllAnalysts();
  public List getUnratedStocks();
  public void addStockRating(String ticker, Integer analystId,
    String rating);  
  public void addAnalyst(Integer id, String name);
  public void addStock(String ticker, String name);
}