package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

// general imports
import java.util.*;

@Stateless
public class StockListBean implements StockList {
  // the reference to the entity manager
  @PersistenceContext
  private EntityManager _manager;
  //the reference to the data source
  @Resource(mappedName="java:/DefaultDS")
  private DataSource ds;

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
    try {
      // make the sql
      StringBuffer sql = new StringBuffer();
      sql.append("SELECT analystId, name ");
      sql.append("FROM Analyst ");
      sql.append("ORDER BY name");

      // get the db connection, statement and result set
      Connection conn = ds.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet results = stmt.executeQuery(sql.toString());

      // get the analysts
      List analystList = new ArrayList();
      while (results.next()) {
        Object[] analystData = new Object[2];
        analystData[0] = new Integer(results.getInt(1));
        analystData[1] = results.getString(2);
        analystList.add(analystData);
      }

      results.close();
      stmt.close();
      conn.close();

      return analystList;
    }
    catch (Exception ex) {
      throw new RuntimeException(ex.getMessage());
    }
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