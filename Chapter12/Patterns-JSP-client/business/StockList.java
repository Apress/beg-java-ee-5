package business;

import javax.ejb.Remote;
import transfer.AnalystTO;
import transfer.StockTO;

// general imports
import java.util.*;

@Remote
public interface StockList {
  // the public business methods on the Stock List bean
  public List getStockRatings();
  public List getAllAnalysts();
  public List getUnratedStocks();
  public void addStockRating(StockTO stockTO);  
  public void addAnalyst(AnalystTO analystTO);
  public void addStock(StockTO stockTO);
}