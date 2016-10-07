package beans;

import beans.Stock;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Stateless
public class StockListBean implements StockList {
  // the reference to the entity manager
  @PersistenceContext
  private EntityManager _manager;

  // the public business methods. these must be coded in the 
  // remote interface also. 

  public String getStock(String ticker) {
    Stock stock = _manager.find(Stock.class, ticker);
    return stock.getName();
  }

  // the finder methods
  public String[] getAllStocks() {
    Query query =
      _manager.createQuery("SELECT s FROM Stock s " +
                           "ORDER BY s.tickerSymbol");
    List stockList = query.getResultList();
    String[] stocks = new String[stockList.size()];
    int j = 0;
    for (int i = 0; i < stockList.size(); i++) {
      Stock stock = (Stock) stockList.get(i);
      stocks[j++] = stock.getTickerSymbol();
    }
    return stocks;
  }

  public String[] getSizeStocks(int siz) {
    Query query =
      _manager.createQuery("SELECT s FROM Stock s " + 
                          "WHERE LENGTH(s.tickerSymbol) = :len " +
                          "ORDER BY s.tickerSymbol");
    query.setParameter("len", siz);
    List stockList = query.getResultList();
    String[] stocks = new String[stockList.size()];
    int j = 0;
    for (int i = 0; i < stockList.size(); i++) {
      Stock stock = (Stock) stockList.get(i);
      stocks[j++] = stock.getTickerSymbol();
    }
    return stocks;
  }
}