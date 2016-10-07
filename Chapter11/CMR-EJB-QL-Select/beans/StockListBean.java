package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

// general imports
import java.util.*;

@Stateless
public class StockListBean implements StockList {
  // the reference to the entity manager
  @PersistenceContext
  private EntityManager _manager;
  
  // the public business methods. these must be coded in the 
  // remote interface also. 
  
  public List getStockRatings() {
    List stkList = new ArrayList();
    Query query =
      _manager.createQuery("SELECT DISTINCT OBJECT(s) " +
                               "FROM Stock s " +
                               "WHERE s.analyst IS NOT NULL " +
                               "ORDER BY s.tickerSymbol");
    // get the rated stocks
    List stocks = query.getResultList();
    for (int i = 0; i < stocks.size(); i++) {
        Stock stock = (Stock) stocks.get(i);
        String[] stockData = new String[4];
        stockData[0] = stock.getTickerSymbol();
        stockData[1] = stock.getName();
        stockData[2] = stock.getAnalyst().getName();
        stockData[3] = stock.getRating();
        stkList.add(stockData);
      }
      
      return stkList;
  }

  public List getAllAnalysts() {
    List analystList = new ArrayList();
    Query query =
      _manager.createQuery("SELECT Object(a) " +
                               "FROM Analyst a " +
                               "ORDER BY a.name");
    // get the analysts
    List analysts = query.getResultList();
    for (int i = 0; i < analysts.size(); i++) {
      Analyst analyst = (Analyst) analysts.get(i);
      Object[] analystData = new Object[2];
      analystData[0] = analyst.getAnalystId();
      analystData[1] = analyst.getName();
      analystList.add(analystData);
    }
      
    return analystList;
  }
  
  public List getUnratedStocks() {
    List stkList = new ArrayList();
    Query query =
      _manager.createQuery("SELECT DISTINCT OBJECT(s) " +
                               "FROM Stock s " +
                               "WHERE s.analyst IS NULL " +
                               "ORDER BY s.tickerSymbol");
    // get the unrated stocks
    List stocks = query.getResultList();
    for (int i = 0; i < stocks.size(); i++) {
      Stock stock = (Stock) stocks.get(i);
      stkList.add(stock.getTickerSymbol());
    }
      
    return stkList;
  }

  public void addStockRating(String ticker, Integer analystId,
    String rating) {
    Stock stock = _manager.find(Stock.class, ticker);
    Analyst analyst = _manager.find(Analyst.class, analystId);
    analyst.assignStock(stock);
    stock.setRating(rating);
  }
  
  public void addAnalyst(Integer id, String name) {
    _manager.persist(new Analyst(id, name));
  }
  
  public void addStock(String ticker, String name) {
    _manager.persist(new Stock(ticker, name));
  }
}