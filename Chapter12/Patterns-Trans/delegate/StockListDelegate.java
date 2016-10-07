package delegate;

import business.StockList;
import transfer.AnalystTO;
import transfer.StockTO;
import javax.naming.InitialContext;
// general imports
import java.util.*;

public class StockListDelegate {
  // reference to singleton delegate
  private static StockListDelegate stockListDelegate;

  // the reference to the stock list bean
  private StockList stockList;

  // private constructor - makes connection to session bean
  private StockListDelegate() throws StockListException {
    try {
      // Get a naming context
      InitialContext ctx = new InitialContext();

      // Get a StockList object
      stockList =
        (StockList) ctx.lookup(StockList.class.getName());
    } catch(Exception e) {
      throw new StockListException(e.getMessage());
    }
  }

  // the business methods. no exposure to actual implementation
  // on the server or the communication method between client and
  // server is hidden to the client.

  public List getStockRatings() throws StockListException {
    try {
      List ratings = stockList.getStockRatings();
      return ratings;
    }
    catch (Exception re) {
      throw new StockListException(re.getMessage());
    }
  }

  public List getAllAnalysts() throws StockListException {
    try {
      List analysts = stockList.getAllAnalysts();
      return analysts;
    }
    catch (Exception re) {
      throw new StockListException(re.getMessage());
    }
  }

  public List getUnratedStocks() throws StockListException {
    try {
      List stocks = stockList.getUnratedStocks();
      return stocks;
    }
    catch (Exception re) {
      throw new StockListException(re.getMessage());
    }
  }

  public void addStockRating(StockTO stock)
    throws StockListException {
    try {
      stockList.addStockRating(stock);
    }
    catch (Exception re) {
      throw new StockListException(re.getMessage());
    }
  }

  public void addAnalyst(AnalystTO analyst)
    throws StockListException {
    try {
      stockList.addAnalyst(analyst);
    }
    catch (Exception re) {
      throw new StockListException(re.getMessage());
    }
  }

  public void addStock(StockTO stock) throws StockListException {
    try {
      stockList.addStock(stock);
    }
    catch (Exception re) {
      throw new StockListException(re.getMessage());
    }
  }

  public static StockListDelegate getInstance()
    throws StockListException {
    if (stockListDelegate == null) {
      stockListDelegate = new StockListDelegate();
    }

    return stockListDelegate;
  }
}